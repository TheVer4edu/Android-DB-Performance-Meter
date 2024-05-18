package com.thever4.dbcomparison.data.converters

import io.realm.kotlin.types.RealmInstant
import java.util.Date
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty

class DateAdapterDelegate(
    private val realmInstantProperty: KMutableProperty<RealmInstant>
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Date {
        return Date(realmInstantProperty.call().epochSeconds)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Date) {
        realmInstantProperty.setter.call(RealmInstant.from(value.time, 0))
    }
}

fun realmDate(property: KMutableProperty<RealmInstant>): DateAdapterDelegate =
    DateAdapterDelegate(property)