package com.example.challenger.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challenger.presentation.auth.pre.PreActivity
import com.example.challenger.presentation.sample.SampleActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, PreActivity::class.java))
        finish()
    }
}
