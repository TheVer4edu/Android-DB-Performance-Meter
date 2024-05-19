package com.thever4.dbcomparison.di

import com.thever4.dbcomparison.data.DatabaseRepository
import com.thever4.dbcomparison.data.NoDBStorageConfigImpl
import com.thever4.dbcomparison.data.NothingDoesDatabaseRepositoryImpl
import com.thever4.dbcomparison.data.StorageConfig
import dagger.Binds
import dagger.Module

@Module
interface FlavorModule {

    @Binds
    fun bindDatabaseRepository(roomRepo: NothingDoesDatabaseRepositoryImpl): DatabaseRepository

    @Binds
    fun bindStorageConfig(config: NoDBStorageConfigImpl): StorageConfig

    companion object {

    }

}