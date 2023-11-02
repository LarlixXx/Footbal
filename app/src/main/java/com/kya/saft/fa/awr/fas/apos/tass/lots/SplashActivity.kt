package com.kya.saft.fa.awr.fas.apos.tass.lots


import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.facebook.FacebookSdk
import com.onesignal.OneSignal
import okhttp3.*
import java.io.IOException

class SplashActivity : AppCompatActivity() {
    var flag = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        OneSignal.initWithContext(this)
        OneSignal.setAppId(Data.oneSignalId)

        FacebookSdk.setApplicationId(Data.fbAppId)
        FacebookSdk.setClientToken(Data.fbToken)
        FacebookSdk.sdkInitialize(getApplication())
        FacebookSdk.setAutoInitEnabled(true)
        FacebookSdk.fullyInitialize()

        get()
        val timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                val intentGame = Intent(applicationContext, MainActivity::class.java)
                val intentWeb = Intent(applicationContext, WebView::class.java)
                if (flag == 1) {
                    startActivity(intentWeb)
                } else {
                    startActivity(intentGame)
                }
            }
        }
        timer.start()

    }

    fun get() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(Data.url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                flag = 0
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (response.isSuccessful) {
                        flag = 1
                    } else {
                        flag = 0
                    }
                }
            }
        })
    }
}