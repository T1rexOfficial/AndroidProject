package com.example.help_me

import android.app.Application
import org.koin.android.ext.android.startKoin

open class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this,listOf(appModule))
    }
}