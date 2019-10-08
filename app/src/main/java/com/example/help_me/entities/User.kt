package com.example.help_me.entities

data class User(
    var login: String? = null,
    var password: String? = null,
    var city: String? = null,
    var gender: String? = null,
    var name: String? = null,
    var surname: String? = null,
    var age: String? = null,
    var photoURL: String? = null
    ){
    constructor() : this(null, null, null, null, null, null, null)
}