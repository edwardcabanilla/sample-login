package com.example.login.framework.network.api.response

import com.squareup.moshi.Json

data class LoginResponse constructor(
    @field:Json(name = "code")
    val code: Int,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "message")
    val message: String,
    @field:Json(name = "data")
    val data: LoginData? = null,
)

data class LoginData constructor(
    @field:Json(name = "user")
    val user: UserData,
    @field:Json(name = "access_token")
    val access_token: String,
)

data class UserData constructor(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "first_name")
    val first_name: String,
    @field:Json(name = "last_name")
    val last_name: String,
    @field:Json(name = "roles")
    val role: UserRoles,
)

data class UserRoles constructor(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
)
