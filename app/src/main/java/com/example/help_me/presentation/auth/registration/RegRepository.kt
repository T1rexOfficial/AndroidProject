package com.example.help_me.presentation.auth.registration

import android.net.Uri
import com.example.help_me.entities.AsyncResult
import com.example.help_me.entities.Company
import com.example.help_me.entities.User
import com.example.help_me.extensions.register
import com.example.help_me.extensions.registerCompany
import com.example.help_me.extensions.uploadFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference

class RegRepository(private val storage: StorageReference, val auth: FirebaseAuth) {

    suspend fun register(user: User): AsyncResult<User> {
        return auth.register(user)
    }
    suspend fun registerCompany(company: Company): AsyncResult<Company> {
        return auth.registerCompany(company)
    }

    suspend fun uploadFile(uri: Uri): AsyncResult<Uri> {
        return storage.uploadFile(uri)
    }
}