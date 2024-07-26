package com.mynt.app.githubclient.network.api

import com.mynt.app.githubclient.network.model.DetailUserResponse
import com.mynt.app.githubclient.network.model.SearchUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 2
    ): Response<SearchUserResponse>

    @GET("/users/{login}")
    suspend fun getUser(
        @Path("login") login: String
    ): Response<DetailUserResponse>
}
