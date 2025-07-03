package com.example.login.repository

import androidx.room.withTransaction
import com.example.login.framework.database.entities.UserDetailsEntity
import com.example.login.framework.database.entities.asWeatherEntity
import com.example.login.framework.database.entities.asWeatherList
import com.example.login.framework.database.room.WeatherDatabase
import com.example.login.framework.network.api.response.LoginData
import com.example.login.framework.network.api.response.LoginResponse
import com.example.login.framework.network.api.response.SignUpResponse
import com.example.login.framework.network.api.response.UserData
import com.example.login.framework.network.api.response.UserRoles
import com.example.login.repository.wrapper.ResponseWrapper
import kotlinx.coroutines.delay
import java.security.MessageDigest
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val db: WeatherDatabase
) : BaseRepository() {

    suspend fun postLogin(username: String, password: String) = safeApiCall {
        val user = db.userDetailsDao.getLocalUserDetails(username)
        val hashedInput = hashPassword(password)
        delay(2000) // Simulate network delay

        if (user.password == hashedInput) {
            LoginResponse(
                code = 200,
                status = "Successful",
                message = "Login Success!",
                data = LoginData(
                    user = UserData(
                        id = 1,
                        username = username,
                        first_name = "Test",
                        last_name = "User",
                        role = UserRoles(
                            id = 1,
                            name = "Admin"
                        )
                    ),
                    access_token ="nAJSdoASfAFhPOAPioasidhuu090-gfnklneuf8u"
                )
            )
        } else {
            LoginResponse(
                code = 403,
                status = "Failed!",
                message = "Invalid Credentials!",
            )
        }
    }

    suspend fun postSignUp(username: String, password: String) = safeApiCall {
        val response = SignUpResponse(
            code = 200,
            status = "Successful",
            message = "Sign Up Success!",
        )
        response.apply {
            insertUserDetails(username, password)
        }
        delay(2000) // Simulate network delay
        response
    }

    private suspend fun insertUserDetails(username: String, password: String) = safeCatching {
        db.apply {
            withTransaction {
                val passwordHash = hashPassword(password)
                userDetailsDao.insertUserDetails(
                    user = UserDetailsEntity(
                        id = 0,
                        username = username,
                        password = passwordHash
                    )
                )
            }
        }
    }

    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }

    suspend fun getUserDetails() = safeApiCall {
        db.userDetailsDao.getLocalUserDetails()
    }
}