package com.thever4.dbcomparison.data

import com.thever4.dbcomparison.data.model.RealmSampleItem
import com.thever4.dbcomparison.data.model.SampleItem
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort
import io.realm.kotlin.types.RealmInstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class RealmDatabaseRepositoryImpl @Inject constructor(
    private val realm: Realm,
) : DatabaseRepository {

    private val fields = mapOf(
        SampleItem.Field.ID to "id",
        SampleItem.Field.NAME to "name",
        SampleItem.Field.DATE to "date",
        SampleItem.Field.DOZE to "doze",
        SampleItem.Field.ACTIVE to "active",
    )

    override suspend fun addItem(item: SampleItem): Unit = withContext(Dispatchers.IO) {
        realm.write {
            val realmSampleItem = RealmSampleItem.fromSampleItem(item)
            copyToRealm(realmSampleItem/*, updatePolicy = UpdatePolicy.ALL*/)
        }
    }

    override suspend fun addItems(item: List<SampleItem>): Unit = withContext(Dispatchers.IO) {
        realm.write {
            item
                .map(RealmSampleItem::fromSampleItem)
                .forEach(this::copyToRealm)
        }
    }

    override suspend fun getItemById(id: Int): SampleItem = withContext(Dispatchers.IO) {
        realm.query<RealmSampleItem>(
            "id == $0",
            id
        )
            .limit(1)
            .find()
            .first()
    }

    override suspend fun getAllItems(): List<SampleItem> = withContext(Dispatchers.IO) {
        realm.query<RealmSampleItem>().find()
    }

    override suspend fun updateItemById(id: Int, item: SampleItem): Unit =
        withContext(Dispatchers.IO) {
            realm.write {
                val realmSampleItem = item as? RealmSampleItem ?: return@write
                copyToRealm(realmSampleItem, updatePolicy = UpdatePolicy.ALL)
            }
        }

    override suspend fun deleteItem(id: Int, item: SampleItem) = withContext(Dispatchers.IO) {
        realm.write {
            val realmSampleItem = item as? RealmSampleItem ?: return@write
            val latestRealmSampleItem = findLatest(realmSampleItem) ?: return@write
            delete(latestRealmSampleItem)
        }
    }

    override suspend fun clearTable() = withContext(Dispatchers.IO) {
        realm.write {
            deleteAll()
        }
    }

    override suspend fun selectOrderingBy(
        field: SampleItem.Field,
        descending: Boolean
    ): List<SampleItem> = withContext(Dispatchers.IO) {
        realm.query<RealmSampleItem>()
            .sort(
                property = if (field != SampleItem.Field.DATE) requireNotNull(fields[field]) else "_realmInstant",
                sortOrder = if (descending) Sort.DESCENDING else Sort.ASCENDING,
            )
            .find()
    }

    override suspend fun selectWhere(field: SampleItem.Field, value: Any): List<SampleItem> =
        withContext(Dispatchers.IO) {
            if (field == SampleItem.Field.DATE)
                realm.query<RealmSampleItem>(
                    query = "_realmInstant == $0",
                    RealmInstant.from((value as Date).time, 0)
                ).find()
            else
                realm.query<RealmSampleItem>(
                    query = "${fields[field]} == $0",
                    value
                ).find()
        }

    override suspend fun emptyRequest(): Unit = withContext(Dispatchers.IO) {
        realm.query<RealmSampleItem>().limit(0).find()
    }


}
