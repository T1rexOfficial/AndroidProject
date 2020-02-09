package com.example.help_me.entities

import java.io.Serializable

data class Company(
    var login: String? = null,
    var password: String? = null,
    var city: String? = null,
    var title: String? = null,
    var website: String? = null,
    var photoURL: String? = null,
    var exp: Int? = null,
    var complReqList: List<Req>? = null
) : Serializable {
    constructor() : this(null, null, null, null, null)
}