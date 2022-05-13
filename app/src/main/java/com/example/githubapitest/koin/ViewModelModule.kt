package com.example.githubapitest.koin

import com.example.githubapitest.viewmodel.GithubApiViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { GithubApiViewModel(get(), get(), get(), androidContext()) }
}