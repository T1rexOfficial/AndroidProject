package com.example.help_me

import com.example.help_me.base.CoroutineProvider
import com.example.help_me.presentation.auth.registration.RegViewModel
import com.example.help_me.presentation.sample.SampleRepository
import com.example.help_me.presentation.sample.SampleViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
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

    factory {
        SampleRepository(get())
    }

    viewModel {
        SampleViewModel(get())
    }

    viewModel {
        RegViewModel()
    }
}