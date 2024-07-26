package com.mynt.app.githubclient.usecase.repo

import com.mynt.app.githubclient.model.Repo
import com.mynt.app.githubclient.repository.repo.RepoRepository
import javax.inject.Inject

class GetReposByUserUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {
    suspend operator fun invoke(userId: String): Result<List<Repo>> {
        return repoRepository.getReposByUser(userId)
    }
}
