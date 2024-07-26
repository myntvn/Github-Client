package com.mynt.app.githubclient.repository.repo

import com.mynt.app.githubclient.model.Repo

interface RepoRepository {
    suspend fun getReposByUser(userId: String): Result<List<Repo>>
}
