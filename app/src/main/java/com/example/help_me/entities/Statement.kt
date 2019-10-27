package com.example.help_me.entities

import java.io.Serializable

data class Req (
    val titleReq: String? = null,
    val addressReq: String? = null,
    val pictureUrls: List<String>? = null
) : Serializable