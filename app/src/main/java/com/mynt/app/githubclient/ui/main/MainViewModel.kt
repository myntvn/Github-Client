package com.mynt.app.githubclient.ui.main

import androidx.lifecycle.ViewModel
import com.mynt.app.githubclient.usecase.user.SearchUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {
}