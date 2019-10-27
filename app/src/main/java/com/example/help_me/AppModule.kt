package com.example.help_me

import android.content.Context
import com.example.help_me.base.CoroutineProvider
import com.example.help_me.presentation.auth.authorization.AuthRepository
import com.example.help_me.presentation.auth.authorization.AuthViewModel
import com.example.help_me.presentation.auth.registration.RegRepository
import com.example.help_me.presentation.auth.registration.RegViewModel
import com.example.help_me.presentation.home.HomeViewModel
import com.example.help_me.presentation.sample.SampleRepository
import com.example.help_me.presentation.sample.SampleViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    factory {
        CoroutineProvider()
    }
    single {
        FirebaseDatabase.getInstance().reference
    }

    single{
        FirebaseAuth.getInstance()
    }

    single {
        androidContext().getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

    single {
        FirebaseStorage.getInstance().reference
    }

    factory {
        SampleRepository(get())
    }

    factory {
        RegRepository(get(), get())
    }

    factory {
        AuthRepository(get())
    }

    viewModel {
        SampleViewModel(get())
    }

    viewModel {
        RegViewModel(get())
    }

    viewModel {
        AuthViewModel(get())
    }

    viewModel {
        HomeViewModel()
    }
}