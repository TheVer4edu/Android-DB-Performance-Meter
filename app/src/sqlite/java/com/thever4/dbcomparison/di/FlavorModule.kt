package com.thever4.dbcomparison.di

import com.thever4.dbcomparison.data.DatabaseRepository
import com.thever4.dbcomparison.data.RawSQLiteDatabaseRepositoryImpl
import com.thever4.dbcomparison.data.RawSQLiteStorageConfigImpl
import com.thever4.dbcomparison.data.StorageConfig
import dagger.Binds
import dagger.Module

@Module
interface FlavorModule {

    @Binds
    fun bindDatabaseRepository(roomRepo: RawSQLiteDatabaseRepositoryImpl): DatabaseRepository

    @Binds
    fun bindStorageConfig(config: RawSQLiteStorageConfigImpl): StorageConfig

    companion object {

    }

}