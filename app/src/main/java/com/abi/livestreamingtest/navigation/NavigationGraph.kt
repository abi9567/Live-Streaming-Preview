package com.abi.livestreamingtest.navigation

import StreamingScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abi.livestreamingtest.screens.homeScreen.HomeScreen

@Composable
fun NavigationGraph(navController : NavHostController) {

    NavHost(navController = navController, startDestination = Screen.StreamingScreen.route) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.StreamingScreen.route) {
            StreamingScreen(navController = navController)
        }
    }
}