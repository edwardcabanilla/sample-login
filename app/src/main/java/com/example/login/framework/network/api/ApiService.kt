package com.example.login.framework.network.api

import com.example.login.BuildConfig
import com.example.login.framework.network.api.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = BuildConfig.API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}