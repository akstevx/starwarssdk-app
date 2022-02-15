package com.example.starwarsdk

import android.app.Activity
import com.example.starwarsdk.utils.Validate

object StarwarsSdk {
    private var sdkInitialized = false

    @Synchronized
     fun initialize(
        activity: Activity
    ) {
        Validate.validateNotNull(activity, "activity")
        Validate.hasInternetPermission(activity)
        if (!isSdkInitialized()){
            sdkInitialized = true
            openSDK(activity)
        }

    }

    private fun isSdkInitialized(): Boolean {
        return sdkInitialized
    }

   private fun openSDK(activity:Activity){
        SdkCoordinator.startIntroActivity(activity = activity)
    }

}