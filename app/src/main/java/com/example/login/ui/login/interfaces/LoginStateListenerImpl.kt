package com.example.login.ui.login.interfaces

import android.content.Intent
import androidx.navigation.NavHostController
import com.example.login.ui.dashboard.DashboardActivity
import com.example.login.ui.login.MainActivity
import com.example.login.ui.login.MainActivity.Companion.LOGIN
import com.example.login.viewmodels.LoginViewModel

class LoginStateListenerImpl : LoginStateListener {
    private var activity: MainActivity? = null
    private var loginViewModel: LoginViewModel? = null
    private var navHostController: NavHostController? = null

    override fun registerActivity(activity: MainActivity) {
        this.activity = activity
    }

    override fun registerNavigation(navController: NavHostController) {
        this.navHostController = navController
    }

    override fun registerLoginViewModel(loginViewModel: LoginViewModel) {
        this.loginViewModel = loginViewModel
    }

    override fun goToForgotPassword() {
        TODO("Not yet implemented")
    }

    override fun login(username: String, password: String) {
        val vm = loginViewModel ?: return

        vm.postLoginAsync(username, password)
    }

    override fun goToLogin() {
        val nav = navHostController ?: return

        nav.navigate(LOGIN)
    }

    override fun goToDashBoard() {
        val act = activity ?: return

        val intent = Intent(act, DashboardActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        act.startActivity(intent)
    }

    override fun goToSignUp() {
        TODO("Not yet implemented")
    }

    override fun loginWithFacebook() {
        TODO("Not yet implemented")
    }

    override fun loginWithGoogle() {
        TODO("Not yet implemented")
    }

    override fun loginWithInstagram() {
        TODO("Not yet implemented")
    }
}
