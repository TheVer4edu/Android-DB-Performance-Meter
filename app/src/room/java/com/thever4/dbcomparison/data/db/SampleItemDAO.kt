package com.thever4.dbcomparison.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.thever4.dbcomparison.data.db.converters.DateConverter
import com.thever4.dbcomparison.data.model.RoomSampleItem
import java.util.Date

@Dao
interface SampleItemDAO {

    @Insert
    suspend fun insertItem(roomSampleItem: RoomSampleItem)

    @Insert
    suspend fun insertItems(roomSampleItems: List<RoomSampleItem>)

    @Delete
    suspend fun deleteItem(roomSampleItem: RoomSampleItem)

    @Update
    suspend fun updateItem(item: RoomSampleItem)

    @Query("SELECT NULL FROM sample_items WHERE 0=1 LIMIT 1")
    suspend fun emptyRequest(): Any?

    @Query("SELECT * FROM sample_items WHERE 1=1")
    suspend fun selectAllItems(): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items WHERE id=:id LIMIT 1")
    suspend fun selectItemById(id: Int): RoomSampleItem

    @Query("SELECT * FROM sample_items WHERE id=:id")
    suspend fun selectItemsById(id: Int): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items WHERE name=:name LIMIT 1")
    suspend fun selectItemByName(name: String): RoomSampleItem

    @Query("SELECT * FROM sample_items WHERE name=:name")
    suspend fun selectItemsByName(name: String): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items WHERE date=:date LIMIT 1")
    @TypeConverters(DateConverter::class)
    suspend fun selectItemByDate(date: Date): RoomSampleItem

    @Query("SELECT * FROM sample_items WHERE date=:date")
    @TypeConverters(DateConverter::class)
    suspend fun selectItemsByDate(date: Date): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items WHERE active=:active LIMIT 1")
    suspend fun selectItemByActive(active: Boolean): RoomSampleItem

    @Query("SELECT * FROM sample_items WHERE active=:active")
    suspend fun selectItemsByActive(active: Boolean): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items WHERE doze=:doze LIMIT 1")
    suspend fun selectItemByDoze(doze: Double): RoomSampleItem

    @Query("SELECT * FROM sample_items WHERE doze=:doze")
    suspend fun selectItemsByDoze(doze: Double): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items ORDER BY id")
    suspend fun selectItemsOrderedById(): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items ORDER BY name")
    suspend fun selectItemsOrderedByName(): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items ORDER BY date")
    suspend fun selectItemsOrderedByDate(): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items ORDER BY active")
    suspend fun selectItemsOrderedByActive(): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items ORDER BY doze")
    suspend fun selectItemsOrderedByDoze(): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items ORDER BY id DESC")
    suspend fun selectItemsOrderedByIdDesc(): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items ORDER BY name DESC")
    suspend fun selectItemsOrderedByNameDesc(): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items ORDER BY date DESC")
    suspend fun selectItemsOrderedByDateDesc(): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items ORDER BY active DESC")
    suspend fun selectItemsOrderedByActiveDesc(): List<RoomSampleItem>

    @Query("SELECT * FROM sample_items ORDER BY doze DESC")
    suspend fun selectItemsOrderedByDozeDesc(): List<RoomSampleItem>

    @Query("DELETE FROM sample_items WHERE 1=1")
    suspend fun clear()

}