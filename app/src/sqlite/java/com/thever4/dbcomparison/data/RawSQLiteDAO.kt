package com.thever4.dbcomparison.data

import android.database.Cursor
import com.thever4.dbcomparison.data.model.RawSQLIteSampleItem
import com.thever4.dbcomparison.data.model.SampleItem
import java.lang.StringBuilder
import java.util.Date
import javax.inject.Inject

class RawSQLiteDAO @Inject constructor(
    private val sqLiteDbHelper: SQLiteDbHelper,
) {

    private val db = sqLiteDbHelper.writableDatabase

    fun addItem(item: RawSQLIteSampleItem) {
        val id = item.id
        val name = item.name.replace("'", "`")
        val date = item.date.time
        val active = if (item.active) 1 else 0
        val doze = item.doze

        val query = Queries.INSERT_ENTITY_BASE + String.format(
            Queries.INSERT_ENTITY_VALUES_FORMAT,
            id, name, date, active, doze
        )
        db.execSQL("$query;")
    }

    fun addItems(items: List<RawSQLIteSampleItem>) {
        val queryBuilder = StringBuilder()
            .append(Queries.INSERT_ENTITY_BASE)

        items.forEachIndexed { index, item ->
            val id = item.id
            val name = item.name.replace("'", "`")
            val date = item.date.time
            val active = if (item.active) 1 else 0
            val doze = item.doze

            val valuesFormatted = String.format(
                Queries.INSERT_ENTITY_VALUES_FORMAT,
                id, name, date, active, doze
            )
            queryBuilder.append(valuesFormatted)
            if (index != items.size - 1)
                queryBuilder.append(", ")
        }

        val query = queryBuilder.toString()
        db.execSQL("$query;")
    }

    private fun processCursorAndClose(cursor: Cursor): MutableList<RawSQLIteSampleItem> =
        with(cursor) {
            val result = mutableListOf<RawSQLIteSampleItem>()
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(SampleItem.Field.ID.fieldName))
                val name = getString(getColumnIndexOrThrow(SampleItem.Field.NAME.fieldName))
                val date = getLong(getColumnIndexOrThrow(SampleItem.Field.DATE.fieldName))
                val active = getInt(getColumnIndexOrThrow(SampleItem.Field.ACTIVE.fieldName))
                val doze = getDouble(getColumnIndexOrThrow(SampleItem.Field.DOZE.fieldName))
                result.add(
                    RawSQLIteSampleItem(
                        id,
                        name,
                        Date(date),
                        active == 1,
                        doze,
                    )
                )
            }
            cursor.close()
            return result
        }

    fun selectAllItems(): MutableList<RawSQLIteSampleItem> {
        val query = Queries.SELECT_QUERY_BASE
        val cursor = db.rawQuery("$query;", arrayOf())
        return processCursorAndClose(cursor)
    }


    fun selectItemsByFieldAndValue(
        field: SampleItem.Field,
        value: Any
    ): MutableList<RawSQLIteSampleItem> {
        val argument: Any = when (value) {
            is Date -> value.time
            is Boolean -> if (value) 1 else 0
            else -> value
        }

        val query = Queries.SELECT_QUERY_BASE + getWhereInfix(field, argument)

        val cursor = db.rawQuery("$query;", arrayOf())
        return processCursorAndClose(cursor)
    }

    private fun getWhereInfix(field: SampleItem.Field, value: Any) =
        String.format(
            if (value is String) Queries.WHERE_EQUAL_FORMAT_STR else Queries.WHERE_EQUAL_FORMAT,
            field.fieldName, value
        )

    private fun constantWhereInfix(const1: Any, const2: Any) =
        String.format(
            Queries.WHERE_EQUAL_FORMAT,
            const1, const2
        )


    fun updateItemByFieldAndValue(field: SampleItem.Field, value: Any) {
        val argument: Any = when (value) {
            is Date -> value.time
            is Boolean -> if (value) 1 else 0
            else -> value
        }

        val query = Queries.UPDATE_BASE + String.format(
            "%s = %s",
            field.fieldName, argument
        )

        db.execSQL("$query;")
    }

    fun updateItem(item: RawSQLIteSampleItem) {
        val id = item.id
        val name = item.name.replace("'", "`")
        val date = item.date.time
        val active = if (item.active) 1 else 0
        val doze = item.doze

        val query = StringBuilder()
            .append(Queries.UPDATE_BASE)
            .append(
                String.format(
                    "%s = %s,",
                    SampleItem.Field.ID.fieldName, id,
                )
            )
            .append(
                String.format(
                    "%s = %s,",
                    SampleItem.Field.NAME.fieldName, name,
                )
            )
            .append(
                String.format(
                    "%s = %s,",
                    SampleItem.Field.DATE.fieldName, date,
                )
            )
            .append(
                String.format(
                    "%s = %s,",
                    SampleItem.Field.ACTIVE.fieldName, active,
                )
            )
            .append(
                String.format(
                    "%s = %s",
                    SampleItem.Field.DOZE.fieldName, doze,
                )
            )
            .toString()

        db.execSQL("$query;")
    }

    fun deleteItemById(id: Int) {
        val query = Queries.DELETE_BASE + getWhereInfix(SampleItem.Field.ID, id)
        db.execSQL("$query;")
    }

    fun deleteItem(item: RawSQLIteSampleItem) =
        deleteItemById(item.id)

    fun clearTable() {
        val query = Queries.DELETE_BASE + constantWhereInfix(1, 1)
        db.execSQL("$query;")
    }

    fun selectOrderedBy(
        field: SampleItem.Field,
        descending: Boolean
    ): MutableList<RawSQLIteSampleItem> {
        val query = Queries.SELECT_QUERY_BASE + String.format(
            Queries.ORDER_BY_FORMAT,
            field.fieldName
        ) + if (descending) Queries.DESCENDING else ""

        val cursor = db.rawQuery("$query;", arrayOf())
        return processCursorAndClose(cursor)
    }

    fun mindlessQuery() {
        val query = Queries.SELECT_QUERY_BASE +
                constantWhereInfix(0, 1) +
                String.format(
                    Queries.LIMIT_FORMAT,
                    0
                )
        db.execSQL("$query;")
    }


}