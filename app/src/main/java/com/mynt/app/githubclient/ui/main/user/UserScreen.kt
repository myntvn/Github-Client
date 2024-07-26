package com.mynt.app.githubclient.ui.main.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mynt.app.githubclient.model.Repo
import com.mynt.app.githubclient.model.User
import com.mynt.app.githubclient.ui.common.compose.LoadingScreen

const val userScreenRoute = "user"

fun NavGraphBuilder.userScreen(
    navHostController: NavHostController
) {
    composable("$userScreenRoute/{userId}") {
        val viewModel = hiltViewModel<UserViewModel>()
        UserScreen(viewModel = viewModel)
    }
}

@Composable
private fun UserScreen(
    viewModel: UserViewModel
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    when (screenState) {
        UserScreenState.Loading -> LoadingScreen()

        is UserScreenState.Ready -> UserScreenContent(
            user = (screenState as UserScreenState.Ready).user,
            repos = (screenState as UserScreenState.Ready).repos,
        )

        UserScreenState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Loading Error")
            }
        }
    }
}

@Composable
private fun UserScreenContent(
    modifier: Modifier = Modifier,
    user: User,
    repos: List<Repo>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        UserInfo(user = user, reposCount = repos.size)

        RepoList(repos = repos)
    }
}
