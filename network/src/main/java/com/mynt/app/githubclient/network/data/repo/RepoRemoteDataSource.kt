package com.mynt.app.githubclient.network.data.repo

import com.mynt.app.githubclient.model.Repo

interface RepoRemoteDataSource {
    suspend fun getReposByUser(userId: String): Result<List<Repo>>
}
