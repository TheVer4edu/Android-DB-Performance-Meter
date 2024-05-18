package com.thever4.dbcomparison.di

import com.thever4.dbcomparison.data.DatabaseRepository
import com.thever4.dbcomparison.data.RawSQLiteDatabaseRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface FlavorModule {

    @Binds
    fun bindDatabaseRepository(roomRepo: RawSQLiteDatabaseRepositoryImpl): DatabaseRepository

    companion object {

    }

}