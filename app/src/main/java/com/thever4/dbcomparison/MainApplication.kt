package com.thever4.dbcomparison

import android.app.Application
import com.thever4.dbcomparison.di.AppComponent
import com.thever4.dbcomparison.di.DaggerAppComponent

class MainApplication: Application() {

    val launchAtTime: Long

    init {
        launchAtTime = System.currentTimeMillis()
    }

    lateinit var appComponent: AppComponent
        private set


    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        super.onCreate()
    }

}