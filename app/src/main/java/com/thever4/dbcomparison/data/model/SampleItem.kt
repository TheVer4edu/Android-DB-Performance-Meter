package com.thever4.dbcomparison.data.model

import java.util.Date

open class SampleItem(
    open val id: Int,
    open val name: String,
    open val date: Date,
    open val active: Boolean,
    open val doze: Double,
) {
    enum class Field {
        ID, NAME, DATE, ACTIVE, DOZE;
    }
}