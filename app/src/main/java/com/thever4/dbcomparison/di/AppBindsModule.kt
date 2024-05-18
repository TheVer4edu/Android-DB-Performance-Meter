package com.thever4.dbcomparison.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
interface AppBindsModule {

    companion object {
        @Provides
        fun provideContext(app: Application): Context =
            app.applicationContext
    }

}