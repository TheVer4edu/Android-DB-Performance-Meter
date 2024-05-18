package com.thever4.dbcomparison.data

import com.thever4.dbcomparison.data.model.SampleItem

interface DatabaseRepository {
    suspend fun addItem(item: SampleItem)
    suspend fun addItems(item: List<SampleItem>)
    suspend fun getItemById(id: Int): SampleItem
    suspend fun getAllItems(): List<SampleItem>
    suspend fun updateItemById(id: Int = -1, item: SampleItem)
    suspend fun deleteItem(id: Int = -1, item: SampleItem)
    suspend fun clearTable()
    suspend fun selectOrderingBy(field: SampleItem.Field, descending: Boolean): List<SampleItem>
    suspend fun selectWhere(field: SampleItem.Field, value: Any): List<SampleItem>
    suspend fun emptyRequest()
}