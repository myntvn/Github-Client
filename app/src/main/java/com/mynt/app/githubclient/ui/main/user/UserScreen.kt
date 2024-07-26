package com.mynt.app.githubclient.ui.main.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
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
            user = (screenState as UserScreenState.Ready).user
        )

        UserScreenState.Error -> {
            Text(text = "Loading Error")
        }
    }
}

@Composable
private fun UserScreenContent(
    modifier: Modifier = Modifier,
    user: User
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        UserInfo(user = user)
    }
}

@Composable
private fun UserInfo(
    modifier: Modifier = Modifier,
    user: User
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            model = user.avatar,
            contentDescription = "avatar"
        )

        Column {
            Text(text = user.name)

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(text = "Followers ${user.followers}")

                Spacer(modifier = Modifier.width(24.dp))

                Text(text = "Following ${user.followers}")
            }
        }
    }
}
