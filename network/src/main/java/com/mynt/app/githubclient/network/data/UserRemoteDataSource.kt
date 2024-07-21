package com.mynt.app.githubclient.network.data

import com.mynt.app.githubclient.model.User

interface UserRemoteDataSource {
    suspend fun searchUsers(query: String): Result<List<User>>
}