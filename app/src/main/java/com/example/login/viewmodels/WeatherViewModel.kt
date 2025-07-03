package com.example.login.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.login.framework.network.api.state.WeatherHistoryState
import com.example.login.framework.network.api.state.WeatherState
import com.example.login.repository.WeatherRepository
import com.example.login.repository.wrapper.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
    @Inject
    constructor(
        private val weatherRepository: WeatherRepository
    ) : BaseViewModel() {

    private val _weatherState: MutableStateFlow<WeatherState> =
        MutableStateFlow(WeatherState.Default)
    val weatherState: StateFlow<WeatherState> get() = _weatherState

    private val _weatherHistoryListState: MutableStateFlow<WeatherHistoryState> =
        MutableStateFlow(WeatherHistoryState.Default)
    val weatherHistoryListState: StateFlow<WeatherHistoryState> get() = _weatherHistoryListState

    fun getWeatherAsync(
        longitude: Double,
        latitude: Double,
        onLoad: Boolean,
    ) {
        _weatherState.value = WeatherState.Loading
        viewModelScope.launch {
            when (
                val response = weatherRepository.getWeather(
                    longitude = longitude, latitude = latitude, onLoad = onLoad
                )
            ) {
                is ResponseWrapper.ResponseSuccess -> {
                    _weatherState.value =
                        WeatherState.Success(
                            response.value,
                        )
                } else -> {
                    val error = parseErrorResponse(response)
                    error?.let { wrapper ->
                        _weatherState.value =
                            WeatherState.Failure(
                                throwable = Throwable(""),
                                message = wrapper.message,
                            )
                    }
                }
            }
        }
    }

    fun getWeatherHistoryList(current: Long) {
        _weatherHistoryListState.value = WeatherHistoryState.Loading
        viewModelScope.launch {
            when (
                val response = weatherRepository.getWeatherHistoryList(current = current)
            ) {
                is ResponseWrapper.ResponseSuccess -> {
                    _weatherHistoryListState.value =
                        WeatherHistoryState.Success(
                            response.value,
                        )
                } else -> {
                    val error = parseErrorResponse(response)
                    error?.let { wrapper ->
                        _weatherHistoryListState.value =
                            WeatherHistoryState.Failure(
                                throwable = Throwable(""),
                                message = wrapper.message,
                            )
                    }
                }
            }
        }
    }
}
