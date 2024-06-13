package com.example.jetpackdemo.utils

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckPermission(
    multiplePermissionsState: MultiplePermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA
        )
    ),
    onPermissionGranted: () -> Unit,
    onPermissionDenied: (text: String) -> Unit
) {
    val permissionState = rememberPermissionState(permission = android.Manifest.permission.READ_SMS)

    if (multiplePermissionsState.allPermissionsGranted) {
        // If all permissions are granted, then show screen with the feature enabled

        onPermissionGranted()
    } else {
        if (multiplePermissionsState.shouldShowRationale) {
            showToast(LocalContext.current,"shouldShowRationale")
        } else {
            onPermissionDenied("")
        }
    }
}