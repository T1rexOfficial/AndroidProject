package com.example.help_me.presentation.auth.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.help_me.R
import kotlinx.android.synthetic.main.activity_reg.*

class RegActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        toolbarRegBack.setOnClickListener {
            onBackPressed()
        }
    }
}
