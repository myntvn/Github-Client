package com.mynt.app.githubclient.network.data

import com.mynt.app.githubclient.model.User
import com.mynt.app.githubclient.network.api.GithubApi
import com.mynt.app.githubclient.network.model.toDomainModel
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val api: GithubApi
) : UserRemoteDataSource {

    override suspend fun searchUsers(query: String): Result<List<User>> {
        return try {
            val response = api.searchUsers(query).body()

            val users = response?.items?.map { it.toDomainModel() }

            users?.let { Result.success(users) } ?: Result.failure(Exception())
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

}
