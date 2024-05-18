package com.thever4.dbcomparison.di

import com.thever4.dbcomparison.data.DatabaseRepository
import com.thever4.dbcomparison.data.NothingDoesDatabaseRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface FlavorModule {

    @Binds
    fun bindDatabaseRepository(roomRepo: NothingDoesDatabaseRepositoryImpl): DatabaseRepository

    companion object {

    }

}