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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {
    var query by mutableStateOf("")
        private set

    private var _users = MutableStateFlow(emptyList<User>())
    val users = _users.asStateFlow()

    fun updateQuery(str: String) {
        query = str
    }

    fun search() {
        if (query.isBlank()) return

        viewModelScope.launch {
            val result = searchUsersUseCase(query)
            if (result.isSuccess) {
                _users.value = result.getOrNull() ?: emptyList()
            }
        }
    }
}
