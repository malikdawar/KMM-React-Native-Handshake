package com.awesomeproject

import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.kmm.kmpsharedmodule.MainActivityKMP
import com.kmm.kmpsharedmodule.getPlatform

class KMPBridgeModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName() = "KMPBridge"

    @ReactMethod
    fun launchKMPLoginScreen() {
        val intent = Intent(reactApplicationContext, MainActivityKMP::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        reactApplicationContext.startActivity(intent)
        println("launchKMPLoginScreen: ${getPlatform().name}")
    }
}
