package com.abi.livestreamingtest.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

object Utils {
    fun checkAndRequestCameraPermission(
        context: Context,
        launcher: ManagedActivityResultLauncher<String, Boolean>,
        onAlreadyGranted: () -> Unit
    ) {
        val permissionCheckResult =
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            onAlreadyGranted()
        } else {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    fun openAppSettings(context: Context) {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        )
        context.startActivity(intent)
    }
}