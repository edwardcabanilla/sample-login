package com.example.login.framework.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.login.BuildConfig
import com.example.login.framework.dagger.modules.repository.DatabaseModule
import com.example.login.framework.database.dao.UserDetailsDao
import com.example.login.framework.database.dao.WeatherDao
import com.example.login.framework.database.entities.UserDetailsEntity
import com.example.login.framework.database.entities.WeatherEntity
import com.example.login.framework.database.room.converter.WeatherConverter
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [WeatherEntity::class, UserDetailsEntity::class], version = 1)
@TypeConverters(WeatherConverter:: class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao
    abstract val userDetailsDao: UserDetailsDao
    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null
        private val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.DB_KEY.toCharArray())
        val factory = SupportFactory(passphrase)
        fun getInstance(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    DatabaseModule.DATABASE_NAME
                )
                    .apply {
                        if (BuildConfig.DEBUG) {
                            openHelperFactory(factory)
                        }
                        fallbackToDestructiveMigration()
                    }
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
