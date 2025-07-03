package com.example.login.framework.database.room.converter

import androidx.room.TypeConverter
import com.example.login.framework.network.api.response.WeatherResponse
import com.google.gson.Gson

class WeatherConverter {
    @TypeConverter
    fun toWeatherObject(string: String?): WeatherResponse? {
        return Gson().fromJson(string, WeatherResponse::class.java)
    }

    @TypeConverter
    fun toWeatherObjectString(content: WeatherResponse?): String? {
        return Gson().toJson(content)
    }
}