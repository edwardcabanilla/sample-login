package com.example.login.framework.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.login.framework.network.api.response.WeatherResponse

@Entity(tableName = "WEATHER_LIST", indices = [ Index(value = ["id"], unique = true) ])
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val weather: WeatherResponse
)

fun WeatherEntity.asWeatherResponse(): WeatherResponse {
    return this.weather
}

fun WeatherResponse.asWeatherEntity(): WeatherEntity{
    return WeatherEntity(
        0,
        weather = this
    )
}

fun List<WeatherEntity>.asWeatherList(): List<WeatherResponse> {
    return map { it.weather }
}
