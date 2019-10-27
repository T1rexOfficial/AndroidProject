package com.example.help_me.presentation.settings


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.help_me.App

import com.example.help_me.R
import com.example.help_me.base.BaseFragment
import com.example.help_me.entities.Company
import com.example.help_me.entities.User
import com.example.help_me.presentation.auth.pre.PreActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.standalone.inject

class SettingsFragment : BaseFragment() {
    override fun layoutId() = R.layout.fragment_settings

    private val pref: SharedPreferences by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        toolbarSettingsBack.setOnClickListener {
//            activity?.supportFragmentManager?.popBackStack()
//        }

        constSettingsLogout.setOnClickListener {
            storeUser(null)
            storeCompany(null)
            startActivity(Intent(this.activity, PreActivity::class.java))
            activity?.finish()
        }

    }


    private fun storeUser(user: User?) {
        val prefsEditor = pref.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        prefsEditor.putString("myUser", json)
        prefsEditor.apply()
    }

    private fun storeCompany (company: Company?) {
        val prefsEditor = pref.edit()
        val gson = Gson()
        val json = gson.toJson(company)
        prefsEditor.putString("myCompany", json)
        prefsEditor.apply()

    }


}
