package com.example.githubapitest.repository.remote

import com.example.githubapitest.model.GithubApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    //@GET("/search/users?q=lagos&page=20")
    @GET("/search/users")
    suspend fun getFetchApi(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): Response<GithubApiModel>

}