package com.example.ui.screens

import android.annotation.SuppressLint
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import com.example.audio.AudioHelper

private class AndroidAudioBridge(private val audioHelper: AudioHelper?) {
    @JavascriptInterface
    fun speak(text: String) {
        audioHelper?.speak(text)
    }
}

private class AndroidDeviceBridge(private val context: android.content.Context) {
    private val prefs = context.getSharedPreferences("bienvenu2_device_prefs", android.content.Context.MODE_PRIVATE)

    @JavascriptInterface
    fun getHardwareDeviceId(): String {
        var devId = prefs.getString("hardware_device_id", null)
        if (devId.isNullOrEmpty()) {
            val androidId = android.provider.Settings.Secure.getString(
                context.contentResolver,
                android.provider.Settings.Secure.ANDROID_ID
            )
            devId = if (!androidId.isNullOrEmpty() && androidId != "9774d56d682e549c") {
                "hw_$androidId"
            } else {
                "hw_" + java.util.UUID.randomUUID().toString().replace("-", "")
            }
            prefs.edit().putString("hardware_device_id", devId).apply()
        }
        return devId
    }

    @JavascriptInterface
    fun hasDeviceUsedTrial(): Boolean {
        return prefs.getBoolean("device_trial_used", false)
    }

    @JavascriptInterface
    fun markDeviceTrialUsed() {
        prefs.edit().putBoolean("device_trial_used", true).apply()
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    audioHelper: AudioHelper? = null,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .testTag("interactive_webview_container"),
        factory = { context ->
            WebView(context).apply {
                // Ensure layer rendering compatibility across various emulators
                setLayerType(View.LAYER_TYPE_HARDWARE, null)
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    allowFileAccess = true
                    allowContentAccess = true
                    cacheMode = WebSettings.LOAD_DEFAULT
                    useWideViewPort = true
                    loadWithOverviewMode = true
                    databaseEnabled = true
                    builtInZoomControls = false
                    displayZoomControls = false
                }
                addJavascriptInterface(AndroidAudioBridge(audioHelper), "AndroidAudio")
                addJavascriptInterface(AndroidDeviceBridge(context), "AndroidDeviceBridge")
                webViewClient = object : WebViewClient() {
                    override fun onReceivedError(
                        view: WebView?,
                        errorCode: Int,
                        description: String?,
                        failingUrl: String?
                    ) {
                        super.onReceivedError(view, errorCode, description, failingUrl)
                    }
                }
                webChromeClient = WebChromeClient()
                loadUrl("file:///android_asset/index.html")
            }
        },
        update = { _ ->
            // Keep active state
        }
    )
}


