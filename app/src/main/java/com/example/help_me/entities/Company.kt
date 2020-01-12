package com.example.help_me.entities

data class Company(
    var login: String? = null,
    var password: String? = null,
    var city: String? = null,
    var title: String? = null,
    var website: String? = null,
    var photoURL: String? = null,
    var exp: Int? = null,
    var complReqList: List<Req>? = null
    ){
    constructor() : this(null, null, null, null, null)
}