package com.abi.livestreamingtest.screens.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.abi.livestreamingtest.navigation.Screen

@Composable
fun HomeScreen(
    navController : NavController
) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Button(onClick = { navController.navigate(route = Screen.StreamingScreen.route) }) {
            Text(text = "Go Live!!!")
        }
    }
}