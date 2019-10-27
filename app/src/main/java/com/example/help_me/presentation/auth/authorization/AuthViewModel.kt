package com.example.help_me.presentation.auth.authorization

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.help_me.App
import com.example.help_me.base.BaseViewModel
import com.example.help_me.entities.Company
import com.example.help_me.entities.User
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.standalone.inject

class AuthViewModel(val repository: AuthRepository): BaseViewModel() {

    val loginLiveData = MutableLiveData<String>()

    private val pref: SharedPreferences by inject()


    fun login(login: String, password: String) {
        makeRequest({ repository.login(login, password) }) { res ->
            unwrap(res) {
                if(App.company == null){
                    val user: User = it as User
                    loginLiveData.value = "${it.name}, вы успешно авторизовались"
                    storeUser(user)
                }else{
                    val company: Company = it as Company
                    loginLiveData.value = "${it.title}, вы успешно авторизовались!"
                    storeCompany(company)
                }
            }
        }
    }



    private fun storeUser(user: User) {
        val prefsEditor = pref.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        prefsEditor.putString("myUser", json)
        prefsEditor.apply()
    }

    private fun storeCompany (company: Company) {
        val prefsEditor = pref.edit()
        val gson = Gson()
        val json = gson.toJson(company)
        prefsEditor.putString("myCompany", json)
        prefsEditor.apply()

    }



}