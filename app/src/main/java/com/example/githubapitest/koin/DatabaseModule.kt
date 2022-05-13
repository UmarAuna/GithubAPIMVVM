package com.example.githubapitest.koin

import android.app.Application
import androidx.room.Room
import com.example.githubapitest.repository.local.GithubApiDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): GithubApiDatabase {
        return Room.databaseBuilder(application, GithubApiDatabase::class.java, "GithubApiDb")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideGithubApi(database: GithubApiDatabase) = database.githubApiDao

    single { provideDatabase(androidApplication()) }
    single { provideGithubApi(get()) }
}