package com.mynt.app.githubclient.ui.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynt.app.githubclient.model.User
import com.mynt.app.githubclient.usecase.user.SearchUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {
    var query by mutableStateOf("")
        private set

    private val _screenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Ready(emptyList()))
    val screenState = _screenState.asStateFlow()

    fun updateQuery(str: String) {
        query = str
    }

    fun search() {
        if (query.isBlank()) return
        if (_screenState.value is HomeScreenState.Searching) return

        viewModelScope.launch {
            _screenState.update { HomeScreenState.Searching }

            val result = searchUsersUseCase(query)
            if (result.isSuccess) {
                val users = result.getOrNull() ?: emptyList()
                _screenState.update { HomeScreenState.Ready(users) }
            } else {
                _screenState.update { HomeScreenState.Error }
            }
        }
    }
}

sealed interface HomeScreenState {
    data object Searching : HomeScreenState
    data class Ready(val users: List<User>) : HomeScreenState
    data object Error : HomeScreenState
}
