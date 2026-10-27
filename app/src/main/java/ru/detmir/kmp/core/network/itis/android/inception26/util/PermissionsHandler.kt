package ru.detmir.kmp.core.network.itis.android.inception26.util

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import java.lang.ref.WeakReference

class PermissionsHandler(
    private val onPermissionGranted: (String) -> Unit,
    private val onPermissionDenied: (() -> Unit)? = null,
    activity: ComponentActivity
) {
    private var weakReference: WeakReference<ComponentActivity>? = WeakReference(activity)
    private var currentPermission: String = ""


    private val singlePermissionContract =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                onPermissionGranted(currentPermission)
            } else {
                onPermissionDenied?.invoke()
            }
        }

    private val multiplePermissionContract =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { resultMap ->
            resultMap.forEach { (permission, granted) ->
                if (granted) {
                    onPermissionGranted(permission)
                } else {
                    onPermissionDenied?.invoke()
                }
            }
        }

    fun requestSinglePermission(permission: String) {
        currentPermission = permission
        singlePermissionContract.launch(permission)
    }

    fun requestMultiplePermissions(permissions: List<String>) {
        multiplePermissionContract.launch(permissions.toTypedArray())
    }

    fun clearReference() {
        weakReference = null
    }
}