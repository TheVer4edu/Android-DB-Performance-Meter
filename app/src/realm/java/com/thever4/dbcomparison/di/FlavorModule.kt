package com.thever4.dbcomparison.di

import com.thever4.dbcomparison.data.DatabaseRepository
import com.thever4.dbcomparison.data.RealmDatabaseRepositoryImpl
import com.thever4.dbcomparison.data.RealmStorageConfigImpl
import com.thever4.dbcomparison.data.StorageConfig
import com.thever4.dbcomparison.data.model.RealmSampleItem
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
interface FlavorModule {

    @Binds
    fun bindDatabaseRepository(roomRepo: RealmDatabaseRepositoryImpl): DatabaseRepository

    @Binds
    fun bindStorageConfig(config: RealmStorageConfigImpl): StorageConfig

    companion object {

        @Provides
        @Singleton
        fun provideRealm(): Realm =
            Realm.open(
                configuration = RealmConfiguration.create(
                    schema = setOf(
                        RealmSampleItem::class,
                    )
                )
            )

    }

}