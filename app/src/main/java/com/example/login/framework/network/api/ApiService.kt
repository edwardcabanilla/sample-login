package com.example.login.framework.network.api

import com.example.login.framework.network.api.response.ContactResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getContactList(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
    ): ContactResponse
}