package com.abi.livestreamingtest.screens.homeScreen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.abi.livestreamingtest.navigation.Screen
import com.abi.livestreamingtest.utils.Utils

@Composable
fun HomeScreen(
    navController: NavController
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            navController.navigate(route = Screen.StreamingScreen.route)
        } else {
            Toast
                .makeText(context, "Allow camera access",
                Toast.LENGTH_SHORT)
                .show()

            Utils.openAppSettings(context = context)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            Utils.checkAndRequestCameraPermission(context,
                launcher,
                onAlreadyGranted = { navController.navigate(route = Screen.StreamingScreen.route) })
        }) {
            Text(text = "Go Live!!!")
        }
    }
}