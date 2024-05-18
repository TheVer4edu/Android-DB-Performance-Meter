package com.thever4.dbcomparison.data.model

import java.util.Date

open class SampleItem(
    open val id: Int = 0,
    open val name: String = "",
    open val date: Date = Date(),
    open val active: Boolean = false,
    open val doze: Double = Double.NaN,
) {
    enum class Field(val fieldName: String) {
        ID("id"),
        NAME("name"),
        DATE("date"),
        ACTIVE("active"),
        DOZE("doze");
    }
}