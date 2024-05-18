package com.thever4.dbcomparison.data.model

import java.util.Date

data class RawSQLIteSampleItem(
    override val id: Int,
    override val name: String,
    override val date: Date,
    override val active: Boolean,
    override val doze: Double,
): SampleItem(id, name, date, active, doze) {

    companion object {
        @JvmStatic
        fun fromSampleItem(item: SampleItem) =
            RawSQLIteSampleItem(
                id = item.id,
                name = item.name,
                date = item.date,
                active = item.active,
                doze = item.doze,
            )

    }

}