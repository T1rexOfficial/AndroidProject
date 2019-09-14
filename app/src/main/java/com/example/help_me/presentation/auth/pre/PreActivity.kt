package com.example.help_me.presentation.auth.pre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.help_me.R
import com.example.help_me.presentation.auth.authorization.AuthActivity
import com.example.help_me.presentation.auth.registration.RegActivity
import kotlinx.android.synthetic.main.activity_pre.*

class PreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre)

        btnReg.setOnClickListener{
            startActivity(Intent(this, RegActivity::class.java))
        }
        btnIn.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }
       }
}
