package com.example.chatappfirebase.SplachSreen.Activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieAnimationView
import com.example.chatappfirebase.R


class SplachScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splach_screen)

        val animationView: LottieAnimationView = findViewById(R.id.animation_view)
//        animationView.setAnimation("chat.json")
        animationView.loop(true)
        animationView.playAnimation()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 1000)
    }
}