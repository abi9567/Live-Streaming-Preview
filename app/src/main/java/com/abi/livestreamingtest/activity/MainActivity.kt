package com.abi.livestreamingtest.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.abi.livestreamingtest.navigation.NavigationGraph
import com.abi.livestreamingtest.ui.theme.LiveStreamingTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            LiveStreamingTestTheme {
                NavigationGraph(navController = navController)
            }
        }
    }
}