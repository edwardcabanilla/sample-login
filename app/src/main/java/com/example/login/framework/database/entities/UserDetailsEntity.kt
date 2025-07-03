package com.example.login.framework.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.login.framework.database.dao.UserDetailsDao
import com.example.login.framework.network.api.response.SignUpResponse
import com.example.login.framework.network.api.response.WeatherResponse

@Entity(tableName = "USER_DETAILS", indices = [ Index(value = ["id"], unique = true) ])
data class UserDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val password: String
)
