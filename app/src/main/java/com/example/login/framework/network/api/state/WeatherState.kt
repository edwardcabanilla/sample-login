package com.example.login.framework.network.api.state

import com.example.login.framework.network.api.response.WeatherResponse

sealed class WeatherState {
    data class Success(
        val data: WeatherResponse,
    ) : WeatherState()

    data class Failure constructor(val throwable: Throwable?, val message: String) :
        WeatherState()

    object Loading : WeatherState()

    object Default : WeatherState()
}
