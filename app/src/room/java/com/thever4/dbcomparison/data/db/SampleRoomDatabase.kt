package com.thever4.dbcomparison.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thever4.dbcomparison.data.model.RoomSampleItem

@Database(
    entities = [
        RoomSampleItem::class,
    ],
    version = 1,
)
abstract class SampleRoomDatabase: RoomDatabase() {
    abstract val dao: SampleItemDAO
}