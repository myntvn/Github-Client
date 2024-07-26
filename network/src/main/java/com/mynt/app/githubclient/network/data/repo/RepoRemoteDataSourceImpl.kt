package com.mynt.app.githubclient.network.data.repo

import com.mynt.app.githubclient.model.Repo
import com.mynt.app.githubclient.network.api.GithubApi
import com.mynt.app.githubclient.network.model.toDomainModel
import javax.inject.Inject

class RepoRemoteDataSourceImpl @Inject constructor(
    private val api: GithubApi
) : RepoRemoteDataSource {

    override suspend fun getReposByUser(userId: String): Result<List<Repo>> {
        return try {
            val response = api.getRepos(userId)

            val repos = response.body()
                ?.filterNot { it.fork }
                ?.map { it.toDomainModel() }

            repos?.let { Result.success(repos) } ?: Result.failure(Exception())
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

}
