package com.debin.gitterandroid.ui.commits

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.debin.gitterandroid.models.Commit
import com.debin.gitterandroid.models.GitRepository
import com.debin.gitterandroid.repositories.ISearchGitRepo
import com.debin.gitterandroid.utils.Resource

private const val TAG = "CommitViewModel"
class CommitsViewModel(private val iSearchGitRepo: ISearchGitRepo) : ViewModel() {


    fun getCommit(userName: String, gitRepository: GitRepository, token:String? = null): LiveData<Resource<List<Commit>>> {
        Log.d(TAG, "getCommit: ${gitRepository.name}")
        return iSearchGitRepo.getCommits(userName,gitRepository,token)
    }
}