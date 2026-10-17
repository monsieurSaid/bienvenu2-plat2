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


