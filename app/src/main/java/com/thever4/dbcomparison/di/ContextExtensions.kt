package com.thever4.dbcomparison.di

import android.content.Context
import com.thever4.dbcomparison.MainApplication


val Context.appComponent: AppComponent
    get() = when(this) {
        is MainApplication -> this.appComponent
        else -> this.applicationContext.appComponent
    }