package com.example.help_me.entities

data class Company(
    var login: String? = null,
    var password: String? = null,
    var city: String? = null,
    var title: String? = null,
    var website: String? = null,
    var photoURL: String? = null
){
    constructor() : this(null, null, null, null, null)
}