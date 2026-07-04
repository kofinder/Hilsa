package com.finderbar.hilsa.app

import android.app.Application
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInitializer @Inject constructor() {
    fun init(application: Application) {
        // Initialize components like Logger, etc.
    }
}