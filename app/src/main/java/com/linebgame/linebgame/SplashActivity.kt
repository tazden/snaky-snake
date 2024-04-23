package com.linebgame.linebgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieAnimationView

class SplashActivity : AppCompatActivity() {
    private lateinit var loadingAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadingAnimationView = findViewById(R.id.loadingAnimationView)

        loadingAnimationView.playAnimation()

        Handler().postDelayed({
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}