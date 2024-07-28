package com.mynt.app.githubclient.network.api

import com.mynt.app.githubclient.network.model.DetailUserResponse
import com.mynt.app.githubclient.network.model.RepoResponse
import com.mynt.app.githubclient.network.model.SearchUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 50
    ): Response<SearchUserResponse>

    @GET("/users/{user_id}")
    suspend fun getUser(
        @Path("user_id") userId: String
    ): Response<DetailUserResponse>

    @GET("/users/{user_id}/repos")
    suspend fun getRepos(
        @Path("user_id") userId: String
    ): Response<List<RepoResponse>>
}
