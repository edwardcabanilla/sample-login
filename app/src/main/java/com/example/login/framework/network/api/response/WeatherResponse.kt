package com.example.login.framework.network.api.response

import com.squareup.moshi.Json

data class WeatherResponse constructor(
    @field:Json(name = "coord")
    val coord: Coordinate,
    @field:Json(name = "weather")
    val weather: List<Weather>,
    @field:Json(name = "base")
    val base: String,
    @field:Json(name = "main")
    val main: Main,
    @field:Json(name = "visibility")
    val visibility: Int,
    @field:Json(name = "wind")
    val wind: Wind,
    @field:Json(name = "clouds")
    val clouds: Clouds,
    @field:Json(name = "dt")
    val dt: Long,
    @field:Json(name = "sys")
    val sys: Sys,
    @field:Json(name = "timezone")
    val timezone: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "cod")
    val cod: Int,
)

data class Sys constructor(
    @field:Json(name = "type")
    val type: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "sunrise")
    val sunrise: Long,
    @field:Json(name = "sunset")
    val sunset: Long,
)

data class Coordinate constructor(
    @field:Json(name = "lon")
    val lon: Double,
    @field:Json(name = "lat")
    val lat: Double,
)

data class Weather constructor(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "main")
    val main: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "icon")
    val icon: String,
)

data class Main constructor(
    @field:Json(name = "temp")
    val temp: Double,
    @field:Json(name = "feels_like")
    val feels_like: Double,
    @field:Json(name = "temp_min")
    val temp_min: Double,
    @field:Json(name = "temp_max")
    val temp_max: Double,
    @field:Json(name = "pressure")
    val pressure: Int,
    @field:Json(name = "humidity")
    val humidity: Int,
    @field:Json(name = "sea_level")
    val sea_level: Int,
    @field:Json(name = "grnd_level")
    val grnd_level: Int,
)

data class Wind constructor(
    @field:Json(name = "speed")
    val speed: Double,
    @field:Json(name = "deg")
    val deg: Int,
    @field:Json(name = "gust")
    val gust: Double,
)

data class Clouds constructor(
    @field:Json(name = "all")
    val all: Double,
)

