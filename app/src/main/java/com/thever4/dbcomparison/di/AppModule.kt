package com.thever4.dbcomparison.di

import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        FlavorModule::class,
        AppBindsModule::class,
    ]
)
class AppModule