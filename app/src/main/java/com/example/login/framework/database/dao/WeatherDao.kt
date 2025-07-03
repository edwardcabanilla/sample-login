package com.example.login.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.login.framework.database.entities.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM WEATHER_LIST")
    fun getLocalWeather(): List<WeatherEntity>
}
