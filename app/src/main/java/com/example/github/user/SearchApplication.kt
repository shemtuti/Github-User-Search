package com.example.github.user

import android.app.Application
import timber.log.Timber

class SearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
