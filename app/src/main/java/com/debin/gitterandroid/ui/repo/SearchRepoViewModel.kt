package com.debin.gitterandroid.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.debin.gitterandroid.models.GitRepository
import com.debin.gitterandroid.repositories.ISearchGitRepo
import com.debin.gitterandroid.utils.Resource

private const val TAG = "SearchRepoViewModel"
class SearchRepoViewModel(private val iSearchGitRepo: ISearchGitRepo) : ViewModel() {

    fun getPublicRepositoriesByUser(username: String): LiveData<Resource<List<GitRepository>>> {
        Log.d(TAG, "getPublicRepositoriesByUser: ${username}")
        return iSearchGitRepo.getPublicRepositoriesByUser(username)

    }

    fun getPrivateAndPublicRepositories(userName:String, token: String): LiveData<Resource<List<GitRepository>>> {
        return  iSearchGitRepo.getPublicAndPrivateRepositories(userName,token)
    }
}