package com.example.githubapitest.koin

import com.example.githubapitest.repository.repo.GithubApiRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { GithubApiRepository(get(), get()) }
}