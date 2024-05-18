package com.thever4.dbcomparison.data

import com.thever4.dbcomparison.data.db.SampleItemDAO
import com.thever4.dbcomparison.data.model.RoomSampleItem
import com.thever4.dbcomparison.data.model.SampleItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class RoomDatabaseRepositoryImpl @Inject constructor(
    private val dao: SampleItemDAO,
) : DatabaseRepository {
    override suspend fun addItem(item: SampleItem) = withContext(Dispatchers.IO) {
        val roomItem = RoomSampleItem.fromSampleItem(item)
        dao.insertItem(roomItem)
    }

    override suspend fun addItems(item: List<SampleItem>) = withContext(Dispatchers.IO) {
        val roomItems = item.map(RoomSampleItem::fromSampleItem)
        dao.insertItems(roomItems)
    }

    override suspend fun getItemById(id: Int): SampleItem = withContext(Dispatchers.IO) {
        return@withContext dao.selectItemById(id)
    }

    override suspend fun getAllItems(): List<SampleItem> = withContext(Dispatchers.IO) {
        return@withContext dao.selectAllItems()
    }

    override suspend fun updateItemById(id: Int, item: SampleItem) = withContext(Dispatchers.IO) {
        val roomItem = RoomSampleItem.fromSampleItem(item)
        dao.updateItem(roomItem)
    }

    override suspend fun deleteItem(id: Int, item: SampleItem) = withContext(Dispatchers.IO) {
        val roomItem = RoomSampleItem.fromSampleItem(item)
        dao.deleteItem(roomItem)
    }

    override suspend fun clearTable() = withContext(Dispatchers.IO) {
        dao.clear()
    }

    override suspend fun selectOrderingBy(
        field: SampleItem.Field, descending: Boolean
    ): List<SampleItem> = when (field) {
        SampleItem.Field.ID -> if (descending) dao.selectItemsOrderedByIdDesc() else dao.selectItemsOrderedById()
        SampleItem.Field.NAME -> if (descending) dao.selectItemsOrderedByNameDesc() else dao.selectItemsOrderedByName()
        SampleItem.Field.ACTIVE -> if (descending) dao.selectItemsOrderedByActiveDesc() else dao.selectItemsOrderedByActive()
        SampleItem.Field.DATE -> if (descending) dao.selectItemsOrderedByDateDesc() else dao.selectItemsOrderedByDate()
        SampleItem.Field.DOZE -> if (descending) dao.selectItemsOrderedByDozeDesc() else dao.selectItemsOrderedByDoze()
    }

    override suspend fun selectWhere(field: SampleItem.Field, value: Any): List<SampleItem> = when(field) {
        SampleItem.Field.ID -> {
            val id = value as? Int ?: throw IllegalArgumentException("ID is INTEGER")
            dao.selectItemsById(id)
        }
        SampleItem.Field.NAME -> {
            val name = value as? String ?: throw IllegalArgumentException("NAME is STRING")
            dao.selectItemsByName(name)
        }
        SampleItem.Field.ACTIVE -> {
            val active = value as? Boolean ?: throw IllegalArgumentException("ACTIVE is BOOLEAN")
            dao.selectItemsByActive(active)
        }
        SampleItem.Field.DATE -> {
            val date = value as? Date ?: throw IllegalArgumentException("DATE is DATE")
            dao.selectItemsByDate(date)
        }
        SampleItem.Field.DOZE -> {
            val doze = value as? Double ?: throw IllegalArgumentException("DOZE is DOUBLE")
            dao.selectItemsByDoze(doze)
        }
    }

    override suspend fun emptyRequest() {
        dao.emptyRequest()
    }

}
