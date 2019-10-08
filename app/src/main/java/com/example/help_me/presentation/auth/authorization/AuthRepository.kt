package com.example.help_me.presentation.auth.authorization

import com.example.help_me.entities.AsyncResult
import com.example.help_me.entities.Company
import com.example.help_me.entities.User
import com.example.help_me.extensions.login
import com.google.firebase.auth.FirebaseAuth

class AuthRepository(val auth: FirebaseAuth){
    suspend fun login(login: String, password: String): AsyncResult<Any> {
        return auth.login(login, password)
    }
}
