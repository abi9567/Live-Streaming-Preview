package com.abi.livestreamingtest.navigation

sealed class Screen(val route : String) {
    data object HomeScreen : Screen(route = "home_screen")
    data object StreamingScreen : Screen("streaming_screen")
}