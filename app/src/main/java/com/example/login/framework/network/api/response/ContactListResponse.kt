package com.example.login.framework.network.api.response

import com.squareup.moshi.Json

data class ContactResponse constructor(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "per_page")
    val per_page: Int,
    @field:Json(name = "total")
    val total: Int,
    @field:Json(name = "total_pages")
    val total_pages: Int,
    @field:Json(name = "data")
    val data: List<Contact>
)

data class Contact(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "first_name")
    val first_name: String,
    @field:Json(name = "last_name")
    val last_name: String,
    @field:Json(name = "avatar")
    val avatar: String,
)

