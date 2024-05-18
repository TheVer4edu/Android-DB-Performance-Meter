package com.thever4.dbcomparison.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import javax.inject.Inject

private const val DATABASE_NAME = "sample.sqlite.db"
private const val DATABASE_VERSION = 1

class SQLiteDbHelper @Inject constructor(
    private val context: Context,
): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Queries.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}