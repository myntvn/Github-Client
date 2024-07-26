package com.mynt.app.githubclient.usecase.user

import com.mynt.app.githubclient.model.User
import com.mynt.app.githubclient.repository.user.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: String): Result<User> {
        return userRepository.getUser(userId)
    }
}
