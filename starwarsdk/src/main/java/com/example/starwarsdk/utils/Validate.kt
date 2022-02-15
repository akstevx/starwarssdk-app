package com.example.starwarsdk.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager

internal object Validate {
    /**
     * To check for internet permission
     *
     * @param context - Application context for current run
     */
    fun hasInternetPermission(context: Context) {
        validateNotNull(context, "context")
        val pm = context.packageManager
        val hasPermission = pm.checkPermission(
            Manifest.permission.INTERNET,
            context.packageName
        )
        check(hasPermission != PackageManager.PERMISSION_DENIED) {
            "StarWarsSDK requires internet permission. " +
                    "Please add the internet permission to your AndroidManifest.xml"
        }
    }

    fun validateNotNull(arg: Any?, name: String) {
        if (arg == null) {
            throw NullPointerException("Argument '$name' cannot be null")
        }
    }
}
