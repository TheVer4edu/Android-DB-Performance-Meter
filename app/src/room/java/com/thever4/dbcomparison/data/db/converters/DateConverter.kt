package com.thever4.dbcomparison.data.db.converters

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromDate(date: Date): Long =
        date.time

    @TypeConverter
    fun toDate(timestamp: Long): Date =
        Date(timestamp)

}