package com.example.login.ui.login.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.ui.login.MainActivity.Companion.LOGIN
import com.example.login.ui.login.MainActivity.Companion.ONBOARDING
import com.example.login.ui.login.MainActivity.Companion.SIGN_UP
import com.example.login.ui.login.components.login.LoginBuilder
import com.example.login.ui.login.components.onboarding.OnBoardingBuilder
import com.example.login.ui.login.components.signup.SignUpBuilder
import com.example.login.ui.login.interfaces.LoginStateListener
import com.example.login.utils.navigation.NavigationUtil.scaleIntoContainer
import com.example.login.utils.navigation.NavigationUtil.scaleOutOfContainer
import com.example.login.utils.navigation.ScaleTransitionDirection
import com.example.login.viewmodels.LoginViewModel

@Composable
fun LoginNavigation(listener: LoginStateListener, loginViewModel: LoginViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()
        listener.registerNavigation(navController)
        NavHost(
            modifier = Modifier
                .padding(innerPadding),
            navController = navController,
            startDestination = ONBOARDING,
        ) {
            composable(
                route = ONBOARDING,
                enterTransition = {
                    scaleIntoContainer()
                },
                exitTransition = {
                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
                },
            ) {
                OnBoardingBuilder(listener)
            }
            composable(
                route = LOGIN,
                enterTransition = {
                    scaleIntoContainer()
                },
                exitTransition = {
                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
                },
            ) {
                LoginBuilder(
                    listener = listener,
                    loginViewModel = loginViewModel
                )
            }
            composable(
                route = SIGN_UP,
                enterTransition = {
                    scaleIntoContainer()
                },
                exitTransition = {
                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
                },
            ) {
                SignUpBuilder(
                    listener = listener,
                    loginViewModel = loginViewModel
                )
            }
        }
    }
}