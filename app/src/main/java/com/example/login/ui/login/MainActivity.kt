package com.example.login.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.login.framework.network.api.state.LoginState
import com.example.login.ui.login.components.LoginNavigation
import com.example.login.ui.login.interfaces.LoginStateListener
import com.example.login.ui.login.interfaces.LoginStateListenerImpl
import com.example.login.ui.theme.LoginTheme
import com.example.login.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LoginStateListener by LoginStateListenerImpl() {

    private val loginViewModel by viewModels<LoginViewModel>()

    companion object {
        const val ONBOARDING = "ONBOARDING"
        const val LOGIN = "LOGIN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        collectAllState()
        registerActivity(activity = this)
        registerLoginViewModel(loginViewModel = loginViewModel)
        enableEdgeToEdge()
        setContent {
            LoginTheme {
                LoginNavigation(
                    listener = this,
                    loginViewModel = loginViewModel
                )
            }
        }
    }

    private fun collectAllState() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { collectLoginState() }
            }
        }
    }

    private suspend fun collectLoginState() {
        loginViewModel.loginState.collect { state ->
            when (state) {
                is LoginState.Success -> {
                    goToDashBoard()
                }
                is LoginState.Failure -> {

                }
                else -> {}
            }
        }
    }
}

