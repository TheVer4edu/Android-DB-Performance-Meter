package com.thever4.dbcomparison.data

import com.thever4.dbcomparison.data.model.RawSQLIteSampleItem
import com.thever4.dbcomparison.data.model.SampleItem
import java.util.Date
import javax.inject.Inject
import kotlin.random.Random

class RawSQLiteDatabaseRepositoryImpl @Inject constructor(
    private val dao: RawSQLiteDAO,
) : DatabaseRepository {
    override suspend fun addItem(item: SampleItem) {
        dao.addItem(RawSQLIteSampleItem.fromSampleItem(item))
    }

    override suspend fun addItems(item: List<SampleItem>) {
        val items = item.map(RawSQLIteSampleItem::fromSampleItem)
        dao.addItems(items)
    }

    override suspend fun getItemById(id: Int): SampleItem {
        return dao.selectItemsByFieldAndValue(
            SampleItem.Field.ID,
            id
        ).first()
    }

    override suspend fun getAllItems(): List<SampleItem> {
        return dao.selectAllItems()
    }

    override suspend fun updateItemById(id: Int, item: SampleItem) {
        dao.updateItem(RawSQLIteSampleItem.fromSampleItem(item))
    }

    override suspend fun deleteItem(id: Int, item: SampleItem) {
        dao.deleteItem(RawSQLIteSampleItem.fromSampleItem(item))
    }

    override suspend fun clearTable() {
        dao.clearTable()
    }

    override suspend fun selectOrderingBy(
        field: SampleItem.Field,
        descending: Boolean
    ): List<SampleItem> {
        return dao.selectOrderedBy(field, descending)
    }

    override suspend fun selectWhere(field: SampleItem.Field, value: Any): List<SampleItem> {
        return dao.selectItemsByFieldAndValue(field, value)
    }

    override suspend fun emptyRequest() {
        dao.mindlessQuery()
    }
}
