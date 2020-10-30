package com.thilawfabrice.compass

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        goToHomeScreen()
    }

    private fun goToHomeScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, Home::class.java))
        }, 2500)
    }
}