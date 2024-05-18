package com.thever4.dbcomparison.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.thever4.dbcomparison.data.db.converters.DateConverter
import java.util.Date

@Entity(tableName = "sample_items")
@TypeConverters(*[DateConverter::class, ])
data class RoomSampleItem(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    override val name: String,
    override val date: Date,
    override val active: Boolean,
    override val doze: Double
): SampleItem(
    id, name, date, active, doze
) {

    companion object {
        @JvmStatic
        fun fromSampleItem(item: SampleItem) =
            RoomSampleItem(
                id = item.id,
                name = item.name,
                date = item.date,
                active = item.active,
                doze = item.doze,
            )
    }

}