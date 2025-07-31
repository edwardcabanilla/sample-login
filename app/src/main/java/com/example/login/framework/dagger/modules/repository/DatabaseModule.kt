package com.example.login.framework.dagger.modules.repository

import android.content.Context
import com.example.login.framework.database.room.SampleDatabase
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
    fun providesSampleDatabase(@ApplicationContext context: Context): SampleDatabase {
        return SampleDatabase.getInstance(context = context)
    }
}
