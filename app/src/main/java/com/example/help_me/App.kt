package com.example.help_me

import android.app.Application
import com.example.help_me.entities.Company
import com.example.help_me.entities.User
import com.google.firebase.auth.FirebaseUser
import org.koin.android.ext.android.startKoin

open class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this,listOf(appModule))
    }

    companion object {
        var firebaseUser: FirebaseUser? = null
        var user: User? = null
        var company: Company? = null
    }
}