package com.example.help_me.presentation.auth.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.help_me.R
import kotlinx.android.synthetic.main.activity_reg.*

class RegActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        toolbarRegBack.setOnClickListener {
            onBackPressed()
        }

        reg_radio_button.setOnCheckedChangeListener { reg_radio_button, i->
            when (i) {
                R.id.reg_radio_button_human -> {
                    reg_const_company.visibility = View.GONE
                    reg_const_person.visibility = View.VISIBLE
                }
                R.id.reg_radio_button_company -> {
                    reg_const_company.visibility = View.VISIBLE
                    reg_const_person.visibility = View.GONE
                }
            }
        }
    }
}
