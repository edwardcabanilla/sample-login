package com.example.login.ui.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.login.framework.network.api.response.Weather
import com.example.login.framework.network.api.state.WeatherHistoryState
import com.example.login.framework.network.api.state.WeatherState
import com.example.login.ui.dashboard.interfaces.DashboardStateListener
import com.example.login.ui.generic.LoadingBuilder
import com.example.login.viewmodels.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun DashBoardBuilder(weatherViewModel: WeatherViewModel, listener: DashboardStateListener) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        WeatherApp(
            padding = padding,
            weatherViewModel = weatherViewModel,
            listener = listener
        )
    }
}

@Composable
fun WeatherApp(
    padding: PaddingValues,
    weatherViewModel: WeatherViewModel,
    listener: DashboardStateListener
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Current Weather", "History")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = {
                        listener.loadWeather()
                        selectedTab = index
                              },
                    text = { Text(title) }
                )
            }
        }
        when (selectedTab) {
            0 -> CurrentWeatherTab(weatherViewModel = weatherViewModel)
            1 -> HistoryTab(weatherViewModel = weatherViewModel)
        }
    }
}

@Composable
fun CurrentWeatherTab(weatherViewModel: WeatherViewModel) {
    when (val weather = weatherViewModel.weatherState.collectAsState().value) {
        is WeatherState.Success -> {
            val currentWeather = weather.data.weather.first()


            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(32.dp)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(weather.data.name, style = MaterialTheme.typography.headlineLarge)
                    WeatherIcon(weather = currentWeather)
                    Text(currentWeather.description, style = MaterialTheme.typography.bodyLarge)
                    Text("${weather.data.main.temp}°C", style = MaterialTheme.typography.headlineLarge)
                    Text("Sunrise: ${weather.data.sys.sunrise.formatTime()}", style = MaterialTheme.typography.bodyMedium)
                    Text("Sunset: ${weather.data.sys.sunset.formatTime()}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        is WeatherState.Loading -> {
            LoadingBuilder()
        }
        else -> { }
    }
}

fun Long.formatTime(): String {
    val date = Date(this * 1000) // Convert seconds to milliseconds
    val sdf = SimpleDateFormat("h:mm a", Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault() // or use UTC if needed
    return sdf.format(date)
}

fun Long.formatToMonthDay(): String {
    val date = Date(this * 1000) // If timestamp is in seconds (OpenWeatherMap)
    val sdf = SimpleDateFormat("MM/dd", Locale.getDefault())
    return sdf.format(date)
}

@Composable
fun WeatherIcon(weather: Weather) {
    val iconUrl = "https://openweathermap.org/img/wn/${weather.icon}@2x.png"
    AsyncImage(
        model = iconUrl,
        contentDescription = "Weather Icon",
        modifier = Modifier.size(128.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun HistoryTab(weatherViewModel: WeatherViewModel) {
    val historyList = when (val weatherHistoryList = weatherViewModel.weatherHistoryListState.collectAsState().value) {
        is WeatherHistoryState.Success -> {
            weatherHistoryList.data.map { "${it.dt.formatToMonthDay()} - ${it.main.temp}°C - ${it.weather.first().main}" }
        }
        else -> emptyList()
    }
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(historyList) { item ->
            Text(item, modifier = Modifier.padding(8.dp))
        }
    }
}