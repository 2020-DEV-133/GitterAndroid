package com.debin.gitterandroid.network

import com.bridge.androidtechnicaltest.utils.LiveDataCallAdapterFactory
import com.debin.gitterandroid.utils.GIT_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GitRetrofitInstance {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun retrofitPupil(): IGitRepoService {


        return Retrofit.Builder()
            .baseUrl(GIT_BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(IGitRepoService::class.java)
    }
}