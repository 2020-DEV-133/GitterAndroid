package com.debin.gitterandroid.ui.users

import androidx.lifecycle.*
import com.debin.gitterandroid.models.GitUser
import com.debin.gitterandroid.repositories.ISearchGitRepo
import com.debin.gitterandroid.utils.OpenForTesting
import com.debin.gitterandroid.utils.Resource
import kotlinx.coroutines.flow.onStart

private const val TAG = "UserViewModel"
@OpenForTesting
class UserViewModel(private val searchGitRepo: ISearchGitRepo) : ViewModel() {



    fun getCurrentUsers(): LiveData<Resource<List<GitUser>>> =
         searchGitRepo.getCurrentUsers().onStart {    emit(Resource.loading(null))  }.asLiveData()




    fun removeUser(user: GitUser): LiveData<Resource<GitUser>> {
        return searchGitRepo.deleteUser(user)
    }







}