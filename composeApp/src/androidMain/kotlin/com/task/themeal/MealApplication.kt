package com.task.themeal

import android.app.Application
import com.task.themeal.di.setupKoin
import org.koin.android.ext.koin.androidContext

class MealApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin {
            androidContext(this@MealApplication)
        }
    }
}