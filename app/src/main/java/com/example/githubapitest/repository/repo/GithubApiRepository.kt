package com.example.githubapitest.repository.repo

import android.util.Log
import com.example.githubapitest.model.GithubApiModel
import com.example.githubapitest.repository.local.GithubApiDao
import com.example.githubapitest.repository.remote.GithubApiService
import com.example.punchandroidtest.extension.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GithubApiRepository(
    private val api: GithubApiService,
    private val dao: GithubApiDao,
) {
    suspend fun getGithubApi(page: Int): Response<GithubApiModel> {
        val response = api.getFetchApi("Lagos", page, 50)
        Log.d(TAG, "${response.body()}")

        response.body()?.let {
            withContext(Dispatchers.IO) {
                dao.deleteAll()
                dao.add(it.items)
            }
        }
        return response
    }
}
