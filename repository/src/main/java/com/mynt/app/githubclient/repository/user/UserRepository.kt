package com.mynt.app.githubclient.repository.user

import com.mynt.app.githubclient.model.User

interface UserRepository {
    suspend fun searchUsers(query: String): Result<List<User>>

    suspend fun getUser(userId: String): Result<User>
}
