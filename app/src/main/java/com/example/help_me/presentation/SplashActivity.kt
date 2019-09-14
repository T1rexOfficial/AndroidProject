package com.example.help_me.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.help_me.presentation.auth.authorization.AuthActivity
import com.example.help_me.presentation.auth.pre.PreActivity
import com.example.help_me.presentation.auth.registration.RegActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, PreActivity::class.java))
        finish()
    }
}
