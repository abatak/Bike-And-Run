package com.batakantonio.bikeandrun.data.util

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BikeAndRunApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Koin
        startKoin {
            androidContext(this@BikeAndRunApplication)
            modules(listOf(databaseModule, repositoryModule, viewModelModule))
        }
    }
}