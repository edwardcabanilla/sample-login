package com.example.login.framework.network.api.state

import com.example.login.framework.network.api.response.SignUpResponse

sealed class SignUpState {
    data class Success(
        val data: SignUpResponse,
    ) : SignUpState()

    data class Failure constructor(val throwable: Throwable?, val message: String) :
        SignUpState()

    object Loading : SignUpState()

    object Default : SignUpState()
}