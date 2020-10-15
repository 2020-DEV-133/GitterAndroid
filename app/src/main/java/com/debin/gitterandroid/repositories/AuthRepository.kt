package com.debin.gitterandroid.repositories

import com.debin.gitterandroid.models.GitUser
import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.debin.gitterandroid.utils.Resource


interface IAuthGitRepository {
    fun login(activity: Activity): LiveData<Resource<GitUser>>
}

private const val TAG = "AuthRepository"

class AuthGitRepository() : IAuthGitRepository {
    override fun login(activity: Activity): LiveData<Resource<GitUser>> {
        val result = MutableLiveData<Resource<GitUser>>()

        return result

    }


}