package com.kya.saft.fa.awr.fas.apos.tass.lots

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebView : AppCompatActivity() {
    private lateinit var webView:android.webkit.WebView
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        webView= findViewById(R.id.webView)
        webView.webViewClient = MyWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.userAgentString = Data.userAgent
        webView.settings.loadWithOverviewMode = true
        webView.settings.domStorageEnabled = true
        webView.loadUrl(Data.url)


    }
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}