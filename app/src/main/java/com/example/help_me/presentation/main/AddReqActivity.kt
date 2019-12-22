package com.example.help_me.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.help_me.R
import kotlinx.android.synthetic.main.activity_add_req.*

class AddReqActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_req)
        toolbarAddReqBack.setOnClickListener {
            onBackPressed()
        }

    }
}
