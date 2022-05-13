package com.example.githubapitest.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubapitest.model.Item

@Dao
interface GithubApiDao {
    @Query("SELECT * FROM Item")
    fun findAll(): List<Item>

    @Query("SELECT * FROM Item")
    fun fetchLocalData(): LiveData<List<Item>>

    @Query("DELETE FROM Item")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(items: List<Item>)
}