package com.example.homeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class ComingSoonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coming_soon)
        val bck = findViewById<Button>(R.id.backbtn)

        window.statusBarColor = ContextCompat.getColor(this, R.color.drk_prpl)

        bck.setOnClickListener {
            this.onBackPressed()
        }
    }
}