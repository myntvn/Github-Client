package com.mynt.app.githubclient.network.data.user

import com.mynt.app.githubclient.model.User
import com.mynt.app.githubclient.network.api.GithubApi
import com.mynt.app.githubclient.network.model.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val api: GithubApi
) : UserRemoteDataSource {

    override suspend fun searchUsers(query: String): Result<List<User>> {
        try {
            val response = api.searchUsers(query)

            val userItems = response.body()?.items ?: return Result.failure(Exception())

            val users = withContext(Dispatchers.Default) {
                userItems.map { userItem ->
                    async {
                        getUser(userItem.login).getOrNull() ?: userItem.toDomainModel()
                    }
                }.awaitAll()
            }

            return Result.success(users)
        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun getUser(userId: String): Result<User> {
        return try {
            val response = api.getUser(userId)

            val user = response.body()?.toDomainModel()

            user?.let { Result.success(user) } ?: Result.failure(Exception())
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

}
