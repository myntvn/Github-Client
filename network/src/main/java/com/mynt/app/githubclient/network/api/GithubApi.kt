package com.mynt.app.githubclient.network.api

import com.mynt.app.githubclient.network.model.UsersResponse
import retrofit2.Response

interface GithubApi {
    fun searchUsers(query: String): Response<UsersResponse>
}
