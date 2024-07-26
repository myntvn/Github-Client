package com.mynt.app.githubclient.ui.main.user

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynt.app.githubclient.model.User
import com.mynt.app.githubclient.usecase.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase
): ViewModel() {
    private val userId: String = checkNotNull(savedStateHandle["userId"])

    private val _screenState = MutableStateFlow<UserScreenState>(UserScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getUserUseCase(userId)

            result.getOrNull()?.let { user ->
                _screenState.value = UserScreenState.Ready(user = user)
            } ?: run {
                _screenState.value = UserScreenState.Error
            }
        }
    }
}

sealed interface UserScreenState {
    data object Loading: UserScreenState
    data class Ready(val user: User): UserScreenState
    data object Error: UserScreenState
}
