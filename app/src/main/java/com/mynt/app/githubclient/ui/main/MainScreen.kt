package com.mynt.app.githubclient.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynt.app.githubclient.ui.theme.GithubClientTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    Text(text = "Hello world")
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainScreen() {
    GithubClientTheme {
        MainScreen()
    }
}
