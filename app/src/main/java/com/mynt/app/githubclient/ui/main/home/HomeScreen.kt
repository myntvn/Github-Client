package com.mynt.app.githubclient.ui.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.mynt.app.githubclient.model.User
import com.mynt.app.githubclient.ui.main.user.userScreenRoute
import com.mynt.app.githubclient.ui.theme.GithubClientTheme

const val homeScreenRoute = "home"
fun NavGraphBuilder.homeScreen(
    navHostController: NavHostController
) {
    composable(homeScreenRoute) {
        val viewModel = hiltViewModel<HomeViewModel>()
        HomeScreen(
            viewModel = viewModel,
            navigateToUserScreen = { userId ->
                navHostController.navigate("$userScreenRoute/${userId}")
            }
        )
    }
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navigateToUserScreen: (String) -> Unit
) {
    val users by viewModel.users.collectAsStateWithLifecycle()

    val keyboardManager = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.query,
            onValueChange = { viewModel.updateQuery(it) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardManager?.hide()
                viewModel.search()
            })
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = users,
                key = { it.id }
            ) { user ->
                UserItem(
                    user = user,
                    onUserClick = navigateToUserScreen
                )
            }
        }
    }
}

@Composable
private fun UserItem(
    modifier: Modifier = Modifier,
    user: User,
    onUserClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                onUserClick(user.id)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            model = user.avatar,
            contentDescription = "avatar",
            contentScale = ContentScale.Crop
        )
        Text(text = user.name)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainScreen() {
    val user = User(
        id = "user1",
        name = "Nguyen Thien My",
        avatar = "https://avatars.githubusercontent.com/u/2",
        followers = 0,
        following = 0
    )

    GithubClientTheme {
        UserItem(
            user = user,
            onUserClick = {}
        )
    }
}
