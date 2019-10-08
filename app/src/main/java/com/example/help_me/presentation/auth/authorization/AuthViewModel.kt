package com.example.help_me.presentation.auth.authorization

import androidx.lifecycle.MutableLiveData
import com.example.help_me.App
import com.example.help_me.base.BaseViewModel
import com.example.help_me.entities.Company
import com.example.help_me.entities.User

class AuthViewModel(val repository: AuthRepository): BaseViewModel() {

    val loginLiveData = MutableLiveData<String>()

    fun login(login: String, password: String) {
        makeRequest({ repository.login(login, password) }) { res ->
            unwrap(res) {
                if(App.company == null){
                    val user: User = it as User
                    loginLiveData.value = "${it.name}, вы успешно авторизовались"
                }else{
                    val user: Company = it as Company
                    loginLiveData.value = "${it.title}, вы успешно авторизовались!"
                }

            }
        }
    }

}