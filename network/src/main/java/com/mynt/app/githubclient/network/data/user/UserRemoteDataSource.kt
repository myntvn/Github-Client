package com.mynt.app.githubclient.network.data.user

import com.mynt.app.githubclient.model.User

interface UserRemoteDataSource {
    suspend fun searchUsers(query: String): Result<List<User>>

    suspend fun getUser(userId: String): Result<User>
}
