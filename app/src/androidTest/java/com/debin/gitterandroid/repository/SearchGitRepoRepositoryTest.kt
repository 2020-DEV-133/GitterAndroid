package com.debin.gitterandroid.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.debin.gitterandroid.models.GitUser
import com.debin.gitterandroid.network.ApiResponse
import com.debin.gitterandroid.network.IGitRepoService
import com.debin.gitterandroid.network.TransferModel.SearchGitUsersContainer
import com.debin.gitterandroid.repositories.AuthGitRepository
import com.debin.gitterandroid.repositories.SearchGitRepoRepository
import com.debin.gitterandroid.ui.addUser.AddUserRepositoryGithubViewModel
import com.debin.gitterandroid.utils.*
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchGitRepoRepositoryTest : SuperGitDatabaseTest() {
    @Mock
    private lateinit var gitRepoService: IGitRepoService
    private lateinit var searchGitRepoRepository: SearchGitRepoRepository

    private lateinit var authGitRepository: AuthGitRepository

    private lateinit var addUserRepositoryGithubViewModel: AddUserRepositoryGithubViewModel


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUpRules() {
        MockitoAnnotations.initMocks(this);
        searchGitRepoRepository = SearchGitRepoRepository(gitRepoService, superGitDatabase)
        authGitRepository = AuthGitRepository()
        addUserRepositoryGithubViewModel = AddUserRepositoryGithubViewModel(authGitRepository, searchGitRepoRepository)
    }

    @Test
    fun test_return_loading_when_try_to_search_user() {
        //Arrange
        searchGitRepoRepository.createDelay = true
        val list = TestUtils.TEST_0_GIT_USER_TRANSFERT
        val call: LiveData<ApiResponse<SearchGitUsersContainer>> =
            ApiUtil.successCall(
                SearchGitUsersContainer(
                    total_count = list.size,
                    incomplete_results = false,
                    users = list
                )
            )

        val liveDataTestUtil = LiveDataTestUtil<Resource<List<GitUser>>>()
        //Act
        `when`(gitRepoService.searchUser(anyString())).thenReturn(call)
        //Verify
        val returnedValue = liveDataTestUtil.getValue(addUserRepositoryGithubViewModel.searchUser(
            list[0].login))
        assertEquals(Resource.Status.LOADING, returnedValue?.status)

    }


    @Test
    fun test_return_success_when_try_to_search_user() {
        //Arrange
        val list = TestUtils.TEST_0_GIT_USER_TRANSFERT
        val call: LiveData<ApiResponse<SearchGitUsersContainer>> =
            ApiUtil.successCall(
                SearchGitUsersContainer(
                    total_count = list.size,
                    incomplete_results = false,
                    users = list
                )
            )

        val liveDataTestUtil = LiveDataTestUtil<Resource<List<GitUser>>>()
        //Act
        `when`(gitRepoService.searchUser(anyString())).thenReturn(call)
        //Verify
        val returnedValue = liveDataTestUtil.getValue(addUserRepositoryGithubViewModel.searchUser(
            list[0].login))
        assertEquals(Resource.Status.SUCCESS, returnedValue?.status)

    }

    @Test
    fun test_return_error_when_try_to_search_user() {
        //Arrange
        val list = TestUtils.TEST_0_GIT_USER_TRANSFERT
        val call: LiveData<ApiResponse<SearchGitUsersContainer>> =
            ApiUtil.errorCall(
                SearchGitUsersContainer(
                    total_count = list.size,
                    incomplete_results = false,
                    users = list
                )
            )

        val liveDataTestUtil = LiveDataTestUtil<Resource<List<GitUser>>>()
        //Act
        `when`(gitRepoService.searchUser(anyString())).thenReturn(call)
        //Verify
        val returnedValue = liveDataTestUtil.getValue(addUserRepositoryGithubViewModel.searchUser(
            list[0].login))
        assertEquals(Resource.Status.ERROR, returnedValue?.status)

    }



}