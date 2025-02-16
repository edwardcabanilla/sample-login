package com.example.login

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        private lateinit var context: Application

        private fun installContext(application: Application) {
            this.context = application
        }

        fun getContext() = context
    }

    override fun onCreate() {
        super.onCreate()
        installContext(application = this)
    }
}