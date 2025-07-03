package com.example.login.ui.dashboard.interfaces

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.login.ui.dashboard.DashboardActivity
import com.example.login.viewmodels.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class DashboardStateListenerImpl : DashboardStateListener {
    private var activity: DashboardActivity? = null
    private var weatherViewModel: WeatherViewModel? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null


    override fun registerActivity(activity: DashboardActivity) {
        this.activity = activity
    }

    override fun registerFusedLocationClient(fusedLocationClient: FusedLocationProviderClient) {
        this.fusedLocationClient = fusedLocationClient
    }

    override fun registerWeatherViewModel(weatherViewModel: WeatherViewModel) {
        this.weatherViewModel = weatherViewModel
    }

    override fun loadWeather(onLoad: Boolean) {
        val act = activity ?: return
        val fused = fusedLocationClient ?: return
        val vm = weatherViewModel ?: return

        if (ActivityCompat.checkSelfPermission(
                act,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(act, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1001)
            return
        }

        fused.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    vm.getWeatherAsync(
                        latitude = it.latitude,
                        longitude = it.longitude,
                        onLoad = onLoad
                    )
                }
            }
    }
}
