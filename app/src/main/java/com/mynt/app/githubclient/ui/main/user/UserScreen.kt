package com.mynt.app.githubclient.ui.main.user

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val userScreenRoute = "user"

fun NavGraphBuilder.userScreen(
    navHostController: NavHostController
) {
    composable("$userScreenRoute/{userId}") {
        val viewModel = hiltViewModel<UserViewModel>()
        UserScreen()
    }
}

@Composable
private fun UserScreen(modifier: Modifier = Modifier) {
    Text(text = "User detail screen")
}