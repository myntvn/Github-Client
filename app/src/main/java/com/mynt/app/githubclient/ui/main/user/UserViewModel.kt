package com.mynt.app.githubclient.ui.main.user

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynt.app.githubclient.model.Repo
import com.mynt.app.githubclient.model.User
import com.mynt.app.githubclient.usecase.repo.GetReposByUserUseCase
import com.mynt.app.githubclient.usecase.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase,
    private val getReposByUserUseCase: GetReposByUserUseCase
): ViewModel() {
    private val userId: String = checkNotNull(savedStateHandle["userId"])

    private val _screenState = MutableStateFlow<UserScreenState>(UserScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            val userDeferred = async { getUserUseCase(userId) }
            val reposDeferred = async { getReposByUserUseCase(userId) }

            val userResult = userDeferred.await()
            val reposResult = reposDeferred.await()

            val user = userResult.getOrNull()
            val repos = reposResult.getOrNull()

            if (user == null || repos == null) {
                _screenState.value = UserScreenState.Error
            } else {
                _screenState.value = UserScreenState.Ready(
                    user = user,
                    repos = repos
                )
            }
        }
    }
}

sealed interface UserScreenState {
    data object Loading: UserScreenState

    data class Ready(
        val user: User,
        val repos: List<Repo>
    ): UserScreenState

    data object Error: UserScreenState
}
