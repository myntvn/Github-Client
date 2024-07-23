package com.mynt.app.githubclient.network.api

import com.mynt.app.githubclient.network.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): Response<UsersResponse>
}
