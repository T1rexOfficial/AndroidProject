package com.example.help_me.presentation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.help_me.App
import com.example.help_me.entities.Company
import com.example.help_me.entities.User
import com.example.help_me.presentation.auth.authorization.AuthActivity
import com.example.help_me.presentation.auth.pre.PreActivity
import com.example.help_me.presentation.auth.registration.RegActivity
import com.example.help_me.presentation.main.MainActivity
import com.google.gson.Gson
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val pref: SharedPreferences by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = getUser()
        val company = getCompany()

        if (user != null || company != null) {
            if (user != null) App.user = user
            else App.company = company
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, PreActivity::class.java))
            finish()
        }

    }

    private fun getUser(): User? {
        val gson = Gson()
        val json = pref.getString("myUser", "")
        val obj = gson.fromJson<User>(json, User::class.java) as? User
        return obj
    }

    private fun getCompany(): Company? {
        val gson = Gson()
        val json = pref.getString("myCompany", "")
        val obj = gson.fromJson<Company>(json, Company::class.java) as? Company
        return obj

    }
}
