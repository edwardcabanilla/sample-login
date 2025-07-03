package com.example.login.repository

import androidx.room.withTransaction
import com.example.login.framework.database.entities.asWeatherEntity
import com.example.login.framework.database.entities.asWeatherList
import com.example.login.framework.database.room.WeatherDatabase
import com.example.login.framework.network.api.ApiService
import com.example.login.framework.network.api.response.WeatherResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: ApiService,
    private val db: WeatherDatabase
) : BaseRepository() {

    suspend fun getWeather(latitude: Double, longitude: Double, onLoad: Boolean) = safeApiCall {
        val response = api.getWeather(
            lat = latitude,
            lon = longitude
        )
        response.apply {
            if (onLoad) insertWeather()
        }
    }

    private suspend fun WeatherResponse.insertWeather() = safeCatching {
        db.apply {
            withTransaction {
                weatherDao.insertWeather(
                    weather = asWeatherEntity()
                )
            }
        }
    }

    suspend fun getWeatherHistoryList(current: Long) = safeApiCall {
        db.weatherDao.getLocalWeather()
            .asWeatherList()
    }
}