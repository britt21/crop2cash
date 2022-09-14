package com.example.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onboarding.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        binding.cropImg.animate().apply {

            alpha(1f).duration = 1000
        }.withEndAction {
            binding.cropTxt.animate().apply {
                alpha(1f).duration = 500
            }.withEndAction {
                val intent = Intent()
                startActivity(intent)
            }
        }.start()

        setContentView(binding.root)

    }
}