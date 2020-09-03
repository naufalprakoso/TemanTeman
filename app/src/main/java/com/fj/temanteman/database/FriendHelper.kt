package com.fj.temanteman.database

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase

import com.fj.temanteman.models.Friend

import java.util.ArrayList

import com.fj.temanteman.database.DatabaseContract.FriendColumns.Companion.ID
import com.fj.temanteman.database.DatabaseContract.FriendColumns.Companion.INITIAL
import com.fj.temanteman.database.DatabaseContract.FriendColumns.Companion.MAJOR
import com.fj.temanteman.database.DatabaseContract.FriendColumns.Companion.NAME
import com.fj.temanteman.database.DatabaseContract.FriendColumns.Companion.PHONE
import com.fj.temanteman.database.DatabaseContract.TABLE_FRIEND
import kotlin.jvm.Throws

class FriendHelper(private val context: Context) {

    private val table = TABLE_FRIEND

    private lateinit var dataBaseHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase

    @Throws(SQLException::class)
    fun open() {
        dataBaseHelper = DatabaseHelper(context)
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()
    }

    fun query(): ArrayList<Friend> {
        val arrayList = ArrayList<Friend>()
        val cursor = database.query(table, null, null, null, null, null, "id DESC", null)
        cursor.moveToFirst()
        var friend: Friend

        if (cursor.count > 0) {
            do {
                friend = Friend()
                friend.id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                friend.initial = cursor.getString(cursor.getColumnIndexOrThrow(INITIAL))
                friend.name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
                friend.major = cursor.getString(cursor.getColumnIndexOrThrow(MAJOR))
                friend.phone = cursor.getString(cursor.getColumnIndexOrThrow(PHONE))

                arrayList.add(friend)
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }

        cursor.close()
        return arrayList
    }

    fun insert(friend: Friend) {
        val initialValues = ContentValues()
        initialValues.put(INITIAL, friend.initial)
        initialValues.put(NAME, friend.name)
        initialValues.put(MAJOR, friend.major)
        initialValues.put(PHONE, friend.phone)
        database.insert(table, null, initialValues)
    }

    fun delete(id: Int) {
        database.delete(table, "id = '$id'", null)
    }
}
