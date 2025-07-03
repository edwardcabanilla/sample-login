package com.example.login.ui.dashboard.interfaces

import com.example.login.ui.dashboard.DashboardActivity
import com.example.login.viewmodels.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient

interface DashboardStateListener {

    fun registerActivity(activity: DashboardActivity)

    fun registerWeatherViewModel(weatherViewModel: WeatherViewModel)

    fun registerFusedLocationClient(fusedLocationClient: FusedLocationProviderClient)

    fun loadWeather(onLoad: Boolean = false)
}
