package com.example.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.homeactivity.HomeActivity
import com.example.onboarding.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        window.statusBarColor = ContextCompat.getColor(this, R.color.prplre)


        binding.cropImg.animate().apply {

            alpha(1f).duration = 2500
        }.withEndAction {
            binding.cropTxt.animate().apply {
                alpha(1f).duration = 3500
            }.withEndAction {
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }
        }.start()

        setContentView(binding.root)

    }
}