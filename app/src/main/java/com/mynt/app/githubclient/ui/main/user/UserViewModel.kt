package com.mynt.app.githubclient.ui.main.user

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel(
) {
    private val userId: String = checkNotNull(savedStateHandle["userId"])

    init {
        Log.i("------userId", userId)
    }
}
