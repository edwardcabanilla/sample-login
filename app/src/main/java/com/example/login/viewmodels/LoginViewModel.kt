package com.example.login.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.login.framework.network.api.state.LoginState
import com.example.login.framework.network.api.state.SignUpState
import com.example.login.repository.LoginRepository
import com.example.login.repository.wrapper.ResponseWrapper
import com.example.login.utils.navigation.StringUtils.isValidateCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val repository: LoginRepository
    ) : BaseViewModel() {

    private val _loginState: MutableStateFlow<LoginState> =
        MutableStateFlow(LoginState.Default)
    val loginState: StateFlow<LoginState> get() = _loginState

    private val _signUpState: MutableStateFlow<SignUpState> =
        MutableStateFlow(SignUpState.Default)
    val signUpState: StateFlow<SignUpState> get() = _signUpState

    fun postLoginAsync(
        username: String,
        password: String,
    ) {
        _loginState.value = LoginState.Loading
        viewModelScope.launch {
            when (
                val response = repository.postLogin(username, password)
            ) {
                is ResponseWrapper.ResponseSuccess -> {
                    _loginState.value = when (response.value.code) {
                        200 -> {
                            LoginState.Success(
                                response.value,
                            )
                        }
                        else -> {
                            LoginState.Failure(
                                throwable = Throwable(""),
                                message = response.value.message,
                            )
                        }
                    }
                } else -> {
                    val error = parseErrorResponse(response)
                    error?.let { wrapper ->
                        _loginState.value =
                            LoginState.Failure(
                                throwable = Throwable(""),
                                message = "Invalid Credentials!",
                            )
                    }
                }
            }
        }
    }

    fun postSignUpAsync(
        username: String,
        password: String,
        confirmPassword: String,
    ) {
        _signUpState.value = SignUpState.Loading

        val result = isValidateCredentials(
            username = username,
            password = password,
            confirmPassword = confirmPassword
        )
        if (
            result.isValid
        ) {
            viewModelScope.launch {
                when (
                    val response = repository.postSignUp(username, password)
                ) {
                    is ResponseWrapper.ResponseSuccess -> {
                        _signUpState.value =
                            SignUpState.Success(
                                response.value,
                            )
                    } else -> {
                        val error = parseErrorResponse(response)
                        error?.let { wrapper ->
                            _signUpState.value =
                                SignUpState.Failure(
                                    throwable = Throwable(""),
                                    message = wrapper.message,
                                )
                        }
                    }
                }
            }
        } else {
            _signUpState.value =
                SignUpState.Failure(
                    throwable = Throwable(""),
                    message = result.errors.joinToString("\n"),
                )
        }
    }
}
