package com.example.homeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homeactivity.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        binding.shimmer.apply {
            showShimmerAdapter()
        }

        setContentView(binding.root)
    }
}