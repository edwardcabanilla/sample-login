package com.example.login.ui.login.interfaces

import androidx.navigation.NavHostController
import com.example.login.ui.login.MainActivity
import com.example.login.viewmodels.LoginViewModel

interface LoginStateListener {

    fun registerActivity(activity: MainActivity)

    fun registerNavigation(navController: NavHostController)

    fun registerLoginViewModel(loginViewModel: LoginViewModel)

    fun goToForgotPassword()

    fun login(
        username: String,
        password: String,
    )

    fun goToLogin()

    fun goToDashBoard()

    fun goToSignUp()

    fun loginWithFacebook()

    fun loginWithGoogle()

    fun loginWithInstagram()
}
