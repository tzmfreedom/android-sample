package com.freedom_man.standard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        try {
//            Thread.sleep(2000)
//        } catch (e: InterruptedException) {
//
//        }
//        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.recent_tweet)
    }
}
