package com.example.login.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.login.framework.database.entities.UserDetailsEntity
import com.example.login.framework.database.entities.WeatherEntity

@Dao
interface UserDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetails(user: UserDetailsEntity)

    @Query("SELECT * FROM USER_DETAILS")
    fun getLocalUserDetails(): List<UserDetailsEntity>

    @Query("SELECT * FROM USER_DETAILS WHERE username = :username")
    fun getLocalUserDetails(username: String): UserDetailsEntity
}
