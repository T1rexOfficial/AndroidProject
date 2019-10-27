package com.example.help_me.extensions

import android.net.Uri
import com.example.help_me.App
import com.example.help_me.entities.AsyncResult
import com.example.help_me.entities.Company
import com.example.help_me.entities.Table
import com.example.help_me.entities.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend inline fun <reified T> DatabaseReference.postData(tableName: String, data: T): AsyncResult<T> {
    return withContext(Dispatchers.Default) {
        suspendCoroutine<AsyncResult<T>> { continuation ->
            this@postData.child(tableName).push().key?.let { this@postData.child(tableName).child(it).setValue(data) }
                ?.addOnSuccessListener {
                    continuation.resume(AsyncResult.Success(data))
                }?.addOnFailureListener {
                    continuation.resume(AsyncResult.Error(it.localizedMessage.orEmpty(), 0))
                }
        }
    }
}

suspend inline fun <reified T> DatabaseReference.getData(tableName: String): AsyncResult<List<T>> {
    return withContext(Dispatchers.Default) {
        suspendCoroutine<AsyncResult<List<T>>> { continuation ->
            this@getData.child(tableName).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    continuation.resume(AsyncResult.Error(error.message, error.code))
                }

                override fun onDataChange(data: DataSnapshot) {
                    val list = arrayListOf<T>()
                    data.children.map {
                        list.add(it.getValue(T::class.java) as T)
                    }
                    continuation.resume(AsyncResult.Success(list))
                }
            })
        }
    }
}

suspend inline fun FirebaseAuth.login(login: String, password: String): AsyncResult<Any> {
    return withContext(Dispatchers.Default) {
        suspendCoroutine<AsyncResult<Any>> { continuation ->
            this@login.signInWithEmailAndPassword(login, password)
                .addOnSuccessListener {
                    val id = it?.user?.uid.orEmpty()
                    App.firebaseUser = this@login.currentUser
                    FirebaseDatabase.getInstance().reference.child(Table.USER).child(id)
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                                FirebaseDatabase.getInstance().reference.child(Table.COMPANY).child(id)
                                    .addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onCancelled(p0: DatabaseError) {
                                            continuation.resume(AsyncResult.Error("no user", 0))
                                        }

                                        override fun onDataChange(p0: DataSnapshot) {
                                            val company = p0.getValue(Company::class.java)
                                            App.company = company
                                            continuation.resume(AsyncResult.Success(company))
                                        }
                                    })
                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                val user = p0.getValue(User::class.java)

                                if(user == null){
                                    FirebaseDatabase.getInstance().reference.child(Table.COMPANY).child(id)
                                        .addListenerForSingleValueEvent(object : ValueEventListener {
                                            override fun onCancelled(p0: DatabaseError) {
                                                continuation.resume(AsyncResult.Error("no user", 0))
                                            }

                                            override fun onDataChange(p0: DataSnapshot) {
                                                val company = p0.getValue(Company::class.java)
                                                App.company = company
                                                continuation.resume(AsyncResult.Success(company))
                                            }
                                        })
                                }else{
                                    App.user = user
                                    continuation.resume(AsyncResult.Success(user))
                                }
                            }
                        })
                }.addOnFailureListener {
                    continuation.resume(AsyncResult.Error(it.localizedMessage.orEmpty(), 0))
                }
        }
    }
}


suspend inline fun FirebaseAuth.register(user: User): AsyncResult<User> {
    return withContext(Dispatchers.Default) {
        suspendCoroutine<AsyncResult<User>> { continuation ->
            this@register.createUserWithEmailAndPassword(user.login.orEmpty(), user.password.orEmpty())
                .addOnSuccessListener {
                    val id = it?.user?.uid.orEmpty()
                    App.firebaseUser = this@register.currentUser
                    App.user = user
                    FirebaseDatabase.getInstance().reference.child(Table.USER).child(id).setValue(user)
                    continuation.resume(AsyncResult.Success(user))
                }.addOnFailureListener {
                    continuation.resume(AsyncResult.Error(it.localizedMessage.orEmpty(), 0))
                }
        }
    }
}

suspend inline fun FirebaseAuth.registerCompany(company: Company): AsyncResult<Company> {
    return withContext(Dispatchers.Default) {
        suspendCoroutine<AsyncResult<Company>> { continuation ->
            this@registerCompany.createUserWithEmailAndPassword(company.login.orEmpty(), company.password.orEmpty())
                .addOnSuccessListener {
                    val id = it?.user?.uid.orEmpty()
                    App.firebaseUser = this@registerCompany.currentUser
                    App.company = company
                    FirebaseDatabase.getInstance().reference.child(Table.COMPANY).child(id).setValue(company)
                    continuation.resume(AsyncResult.Success(company))
                }.addOnFailureListener {
                    continuation.resume(AsyncResult.Error(it.localizedMessage.orEmpty(), 0))
                }
        }
    }
}

suspend fun StorageReference.uploadFile(uri: Uri, fileFormat: String = "jpg"): AsyncResult<Uri> {
    return withContext(Dispatchers.Default) {
        suspendCoroutine<AsyncResult<Uri>> { continuation ->
            this@uploadFile.child(Table.STORAGE_PATH_UPLOADS + "-" + System.currentTimeMillis() + ".$fileFormat")
                .putFile(uri)
                .addOnSuccessListener {
                    it.storage.downloadUrl.addOnSuccessListener { link ->
                        continuation.resume(AsyncResult.Success(link))
                    }.addOnFailureListener { error ->
                        continuation.resume(AsyncResult.Error(error.localizedMessage.orEmpty(), 0))
                    }
                }
                .addOnFailureListener {
                    continuation.resume(AsyncResult.Error(it.localizedMessage.orEmpty(), 0))
                }
        }
    }
}
