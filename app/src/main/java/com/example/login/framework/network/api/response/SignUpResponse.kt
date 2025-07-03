package com.example.login.framework.network.api.response

import com.squareup.moshi.Json

data class SignUpResponse constructor(
    @field:Json(name = "code")
    val code: Int,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "message")
    val message: String,
)