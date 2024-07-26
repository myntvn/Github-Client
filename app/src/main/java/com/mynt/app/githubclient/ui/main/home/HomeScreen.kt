package com.mynt.app.githubclient.ui.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mynt.app.githubclient.ui.common.compose.LoadingScreen
import com.mynt.app.githubclient.ui.main.user.userScreenRoute

const val homeScreenRoute = "home"
fun NavGraphBuilder.homeScreen(
    navHostController: NavHostController
) {
    composable(homeScreenRoute) {
        val viewModel = hiltViewModel<HomeViewModel>()
        HomeScreen(
            viewModel = viewModel,
            onUserClick = { userId ->
                navHostController.navigate("$userScreenRoute/${userId}")
            }
        )
    }
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onUserClick: (String) -> Unit
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

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
            placeholder = {
                Text(text = "Search by user name")
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardManager?.hide()
                viewModel.search()
            }),
            singleLine = true
        )

        when (screenState) {
            HomeScreenState.Searching -> LoadingScreen()

            is HomeScreenState.Ready -> {
                val users = (screenState as HomeScreenState.Ready).users
                if (users.isEmpty()) EmptyPlaceHolder()
                else UserList(users = users, onUserClick = onUserClick)
            }

            HomeScreenState.Error -> SearchErrorPlaceHolder()
        }
    }
}

@Composable
fun EmptyPlaceHolder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No users",
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
private fun SearchErrorPlaceHolder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Search error")
    }
}
