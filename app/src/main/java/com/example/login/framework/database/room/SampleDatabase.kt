package com.example.login.framework.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.login.framework.dagger.modules.repository.DatabaseModule
import com.example.login.framework.database.dao.ContactListDao
import com.example.login.framework.database.entities.ContactListEntity
import com.example.login.framework.database.room.converter.ContactListConverter

@Database(entities = [ContactListEntity::class], version = 1)
@TypeConverters(ContactListConverter:: class)
abstract class SampleDatabase : RoomDatabase() {

    abstract val contactListDao: ContactListDao

    companion object {
        @Volatile
        private var INSTANCE: SampleDatabase? = null
        fun getInstance(context: Context): SampleDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    val builder = Room.databaseBuilder(
                        context.applicationContext,
                        SampleDatabase::class.java,
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
