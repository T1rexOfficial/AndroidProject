package com.example.help_me.presentation.auth.registration

import com.example.help_me.entities.AsyncResult
import com.example.help_me.entities.Company
import com.example.help_me.entities.User
import com.example.help_me.extensions.register
import com.example.help_me.extensions.registerCompany
import com.google.firebase.auth.FirebaseAuth

class RegRepository(val auth: FirebaseAuth) {

    suspend fun register(user: User): AsyncResult<User> {
        return auth.register(user)
    }
    suspend fun registerCompany(company: Company): AsyncResult<Company> {
        return auth.registerCompany(company)
    }
}