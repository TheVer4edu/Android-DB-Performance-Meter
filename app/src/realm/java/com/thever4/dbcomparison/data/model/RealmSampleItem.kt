package com.thever4.dbcomparison.data.model

import com.thever4.dbcomparison.data.converters.realmDate
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.Date

class RealmSampleItem : SampleItem(), RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var _realmInstant = RealmInstant.now()
    override var id: Int = 0
    override var name: String = ""
    @Ignore
    override var date: Date by realmDate(::_realmInstant)
    override var active: Boolean = false
    override var doze: Double = Double.NaN


    companion object {
        @JvmStatic
        fun fromSampleItem(item: SampleItem) =
            RealmSampleItem().apply {
                id = item.id
                name = item.name
                date = item.date
                active = item.active
                doze = item.doze
            }
    }
}