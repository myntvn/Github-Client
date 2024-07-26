package com.mynt.app.githubclient.repository.user

import com.mynt.app.githubclient.model.User
import com.mynt.app.githubclient.network.data.user.UserRemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun searchUsers(query: String): Result<List<User>> {
        return userRemoteDataSource.searchUsers(query)
    }

    override suspend fun getUser(userId: String): Result<User> {
        return userRemoteDataSource.getUser(userId)
    }

}
