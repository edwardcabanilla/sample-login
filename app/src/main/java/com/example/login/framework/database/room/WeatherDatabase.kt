package com.example.login.framework.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.login.framework.dagger.modules.repository.DatabaseModule
import com.example.login.framework.database.dao.UserDetailsDao
import com.example.login.framework.database.dao.WeatherDao
import com.example.login.framework.database.entities.UserDetailsEntity
import com.example.login.framework.database.entities.WeatherEntity
import com.example.login.framework.database.room.converter.WeatherConverter

@Database(entities = [WeatherEntity::class, UserDetailsEntity::class], version = 1)
@TypeConverters(WeatherConverter:: class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao
    abstract val userDetailsDao: UserDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null
        fun getInstance(context: Context): WeatherDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    val builder = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherDatabase::class.java,
                        DatabaseModule.DATABASE_NAME
                    )
                    builder.fallbackToDestructiveMigration()
                    instance = builder.build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}
