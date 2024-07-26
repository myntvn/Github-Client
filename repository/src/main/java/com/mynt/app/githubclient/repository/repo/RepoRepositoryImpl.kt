package com.mynt.app.githubclient.repository.repo

import com.mynt.app.githubclient.model.Repo
import com.mynt.app.githubclient.network.data.repo.RepoRemoteDataSource
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoRemoteDataSource: RepoRemoteDataSource
) : RepoRepository {

    override suspend fun getReposByUser(userId: String): Result<List<Repo>> {
        return repoRemoteDataSource.getReposByUser(userId)
    }

}
