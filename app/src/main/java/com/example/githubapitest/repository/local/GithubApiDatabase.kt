package com.example.githubapitest.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubapitest.model.GithubApiModel
import com.example.githubapitest.model.Item
import com.example.githubapitest.repository.converters.AbstractConverter

@Database(
    entities = [
        GithubApiModel::class,
        Item::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ItemConverter::class
)

abstract class GithubApiDatabase : RoomDatabase() {
    abstract val githubApiDao: GithubApiDao
}

private class ItemConverter : AbstractConverter<Item>(Item::class, Array<Item>::class)
