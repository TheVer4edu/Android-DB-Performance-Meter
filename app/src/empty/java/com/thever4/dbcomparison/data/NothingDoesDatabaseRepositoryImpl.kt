package com.thever4.dbcomparison.data

import com.thever4.dbcomparison.data.model.SampleItem
import java.util.Date
import javax.inject.Inject
import kotlin.random.Random

class NothingDoesDatabaseRepositoryImpl @Inject constructor(
) : DatabaseRepository {
    override suspend fun addItem(item: SampleItem) {
    }

    override suspend fun addItems(item: List<SampleItem>) {
    }

    override suspend fun getItemById(id: Int): SampleItem {
        return SampleItem(
            id = Random.nextInt(),
            name = Random.nextInt().toString(),
            active = Random.nextBoolean(),
            date = Date(),
            doze = Random.nextDouble(),
        )
    }

    override suspend fun getAllItems(): List<SampleItem> {
        return emptyList()
    }

    override suspend fun updateItemById(id: Int, item: SampleItem) {
    }

    override suspend fun deleteItem(id: Int, item: SampleItem) {
    }

    override suspend fun clearTable() {
    }

    override suspend fun selectOrderingBy(
        field: SampleItem.Field,
        descending: Boolean
    ): List<SampleItem> {
        return emptyList()
    }

    override suspend fun selectWhere(field: SampleItem.Field, value: Any): List<SampleItem> {
        return emptyList()
    }

    override suspend fun emptyRequest() {
    }


}
