package com.example.login.framework.network.api.state

import com.example.login.framework.network.api.response.LoginResponse

sealed class LoginState {
    data class Success(
        val data: LoginResponse,
    ) : LoginState()

    data class Failure constructor(val throwable: Throwable?, val message: String) :
        LoginState()

    object Loading : LoginState()

    object Default : LoginState()
}
