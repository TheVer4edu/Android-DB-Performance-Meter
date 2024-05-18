package com.thever4.dbcomparison.data

import com.thever4.dbcomparison.data.model.SampleItem

object Queries {

    const val TABLE_NAME = "sample_items"

    val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
            "${SampleItem.Field.ID.fieldName} INTEGER PRIMARY KEY," +
            "${SampleItem.Field.NAME.fieldName} TEXT," +
            "${SampleItem.Field.DATE.fieldName} INTEGER," +
            "${SampleItem.Field.ACTIVE.fieldName} INTEGER," +
            "${SampleItem.Field.DOZE.fieldName} REAL)"

    val INSERT_ENTITY_BASE = "INSERT INTO $TABLE_NAME (" +
            "${SampleItem.Field.ID.fieldName}," +
            "${SampleItem.Field.NAME.fieldName}," +
            "${SampleItem.Field.DATE.fieldName}," +
            "${SampleItem.Field.ACTIVE.fieldName}," +
            SampleItem.Field.DOZE.fieldName +
            ") VALUES "

    const val INSERT_ENTITY_VALUES_FORMAT = "(" +
            "%d," +
            "'%s'," +
            "%d," +
            "%d," +
            "%f)"

    const val SELECT_QUERY_BASE = "SELECT * FROM $TABLE_NAME "

    const val WHERE_EQUAL_FORMAT = "WHERE %s=%s "
    const val WHERE_EQUAL_FORMAT_STR = "WHERE %s='%s' "

    const val UPDATE_BASE = "UPDATE $TABLE_NAME SET "

    const val DELETE_BASE = "DELETE FROM $TABLE_NAME "

    const val ORDER_BY_FORMAT = "ORDER BY %s "

    const val DESCENDING = "DESC "

    const val LIMIT_FORMAT = "LIMIT %d "

}