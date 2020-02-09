package com.example.help_me.entities

import java.io.Serializable

data class
Req(
    var key: String? = null,
    val titleReq: String? = null,
    val addressd: String? = null,
    val addressReq: String? = null,
    val explainReq: String? = null,
    val maker: User? = null,
    val pictureUrls: List<String>? = null,
    var partListUsers: List<User>? = null,
    var partListCompany: List<Company>? = null
) : Serializable