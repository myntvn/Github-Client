package com.mynt.app.githubclient.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mynt.app.githubclient.ui.main.home.homeScreen
import com.mynt.app.githubclient.ui.main.home.homeScreenRoute
import com.mynt.app.githubclient.ui.main.user.userScreen
import com.mynt.app.githubclient.ui.theme.GithubClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            GithubClientTheme {
                MainNavHost(navController)
            }
        }
    }
}

@Composable
fun MainNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = homeScreenRoute
    ) {
        homeScreen(navHostController)

        userScreen(navHostController)
    }
}