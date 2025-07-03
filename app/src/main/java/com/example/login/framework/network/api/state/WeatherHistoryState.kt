package com.example.login.framework.network.api.state

import com.example.login.framework.network.api.response.WeatherResponse

sealed class WeatherHistoryState {
    data class Success(
        val data: List<WeatherResponse>,
    ) : WeatherHistoryState()

    data class Failure constructor(val throwable: Throwable?, val message: String) :
        WeatherHistoryState()

    object Loading : WeatherHistoryState()

    object Default : WeatherHistoryState()
}
