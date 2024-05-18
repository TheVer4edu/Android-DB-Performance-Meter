package com.thever4.dbcomparison.di

import android.content.Context
import androidx.room.Room
import com.thever4.dbcomparison.data.DatabaseRepository
import com.thever4.dbcomparison.data.RoomDatabaseRepositoryImpl
import com.thever4.dbcomparison.data.db.SampleRoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface FlavorModule {

    @Binds
    fun bindDatabaseRepository(roomRepo: RoomDatabaseRepositoryImpl): DatabaseRepository

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(context: Context): SampleRoomDatabase =
            Room.databaseBuilder(
                context,
                SampleRoomDatabase::class.java,
                "sample.room.db"
            ).build()

        @Provides
        fun provideSampleItemDao(db: SampleRoomDatabase) =
            db.dao
    }

}