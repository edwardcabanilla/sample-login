package com.example.login.ui.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.login.framework.network.api.state.WeatherState
import com.example.login.ui.dashboard.components.DashBoardBuilder
import com.example.login.ui.dashboard.interfaces.DashboardStateListener
import com.example.login.ui.dashboard.interfaces.DashboardStateListenerImpl
import com.example.login.ui.theme.LoginTheme
import com.example.login.viewmodels.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardActivity : ComponentActivity(), DashboardStateListener by DashboardStateListenerImpl() {

    private val weatherViewModel by viewModels<WeatherViewModel>()
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginTheme {
                DashBoardBuilder(
                    weatherViewModel = weatherViewModel,
                    listener = this
                )
            }
        }
        registerActivity(this)
        registerWeatherViewModel(weatherViewModel)
        registerFusedLocationClient(fusedLocationClient)
        initializeApi()
        lifecycleScope.launch { collectWeatherState() }
    }

    private fun initializeApi() {
        loadWeather(onLoad = true)
    }

    private suspend fun collectWeatherState() {
        weatherViewModel.weatherState.collectLatest { state ->
            when (state) {
                is WeatherState.Success -> {
                    weatherViewModel.getWeatherHistoryList(current = state.data.dt)
                }
                else -> {

                }
            }
        }
    }
}