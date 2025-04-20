package com.micahnyabuto.spendsmart

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SpendSmartApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}