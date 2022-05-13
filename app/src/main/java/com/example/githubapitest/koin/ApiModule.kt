package com.example.githubapitest.koin

import com.example.githubapitest.repository.remote.GithubApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideGithubApi(retrofit: Retrofit): GithubApiService {
        return retrofit.create(GithubApiService::class.java)
    }

    single { provideGithubApi(get()) }
}
