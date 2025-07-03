package com.example.login.repository

import com.example.login.framework.network.api.response.LoginData
import com.example.login.framework.network.api.response.LoginResponse
import com.example.login.framework.network.api.response.SignUpResponse
import com.example.login.framework.network.api.response.UserData
import com.example.login.framework.network.api.response.UserRoles
import com.example.login.repository.wrapper.ResponseWrapper
import kotlinx.coroutines.delay

class MockRepository {

    suspend fun postLogin(username: String, password: String): ResponseWrapper<LoginResponse> {
        delay(3000) // Simulate network delay

        return if (username == "test" && password == "password") {
            ResponseWrapper.ResponseSuccess(
                LoginResponse(
                    code = 200,
                    status = "Successful",
                    message = "Login Success!",
                    data = LoginData(
                        user = UserData(
                            id = 1,
                            username = "test",
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
            )

        } else {
            ResponseWrapper.ResponseFailure(
                statusCode = 403,
                errorMessage = "Invalid credentials",
            )
        }
    }

    suspend fun postSignUp(username: String, password: String): ResponseWrapper<SignUpResponse> {
        delay(3000) // Simulate network delay

        return if (username == "test" && password == "password") {
            ResponseWrapper.ResponseSuccess(
                SignUpResponse(
                    code = 200,
                    status = "Successful",
                    message = "Sign Up Success!",
                )
            )

        } else {
            ResponseWrapper.ResponseFailure(
                statusCode = 403,
                errorMessage = "Invalid credentials",
            )
        }
    }

    suspend fun saveUserDetails() {

    }
}