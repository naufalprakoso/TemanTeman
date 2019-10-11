package com.fj.temanteman.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper internal constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FRIEND)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "DbFriend"
        private const val DATABASE_VERSION = 1
        private val SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s" +
                " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL)",
                DatabaseContract.TABLE_FRIEND,
                DatabaseContract.FriendColumns.ID,
                DatabaseContract.FriendColumns.INITIAL,
                DatabaseContract.FriendColumns.NAME,
                DatabaseContract.FriendColumns.MAJOR,
                DatabaseContract.FriendColumns.PHONE
        )
    }
}
