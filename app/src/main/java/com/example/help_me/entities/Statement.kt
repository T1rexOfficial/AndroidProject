package com.example.help_me.entities

import java.io.Serializable

data class
Req (
    val titleReq: String? = null,
    val addressd: String? = null,
    val addressReq: String? = null,
    val explainReq: String? = null,
    val pictureUrls: List<String>? = null,
    val partListUsers: List<User>? = null,
    val partListCompany: List<Company>? = null
) : Serializable