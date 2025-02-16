package com.example.login.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.login.framework.network.api.state.LoginState
import com.example.login.repository.MockRepository
import com.example.login.repository.wrapper.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val repository: MockRepository
    ) : BaseViewModel() {
    private val _loginState: MutableStateFlow<LoginState> =
        MutableStateFlow(LoginState.Default)
    val loginState: StateFlow<LoginState> get() = _loginState

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
                    _loginState.value =
                        LoginState.Success(
                            response.value,
                        )
                } else -> {
                    val error = parseErrorResponse(response)
                    error?.let { wrapper ->
                        _loginState.value =
                            LoginState.Failure(
                                throwable = Throwable(""),
                                message = wrapper.message,
                            )
                    }
                }
            }
        }
    }
}
