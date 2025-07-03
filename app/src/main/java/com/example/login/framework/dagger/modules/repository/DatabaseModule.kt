package com.example.login.framework.dagger.modules.repository

import android.content.Context
import com.example.login.framework.database.room.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    const val DATABASE_NAME = "weather.db"

    @Provides
    fun providesWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return WeatherDatabase.getInstance(context = context)
    }
}
