package com.fj.temanteman.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.fj.temanteman.models.Friend;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.fj.temanteman.database.DatabaseContract.FriendColumns.INITIAL;
import static com.fj.temanteman.database.DatabaseContract.FriendColumns.MAJOR;
import static com.fj.temanteman.database.DatabaseContract.FriendColumns.NAME;
import static com.fj.temanteman.database.DatabaseContract.FriendColumns.PHONE;
import static com.fj.temanteman.database.DatabaseContract.TABLE_FRIEND;

public class FriendHelper {

    private static String DATABASE_TABLE = TABLE_FRIEND;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public FriendHelper(Context context){
        this.context = context;
    }

    public void open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<Friend> query(){
        ArrayList<Friend> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE,
                null,null,null,null,null,_ID +" DESC",null);
        cursor.moveToFirst();
        Friend friend;

        if (cursor.getCount() > 0) {
            do {
                friend = new Friend();
                friend.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                friend.setInitial(cursor.getString(cursor.getColumnIndexOrThrow(INITIAL)));
                friend.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                friend.setMajor(cursor.getString(cursor.getColumnIndexOrThrow(MAJOR)));
                friend.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(PHONE)));

                arrayList.add(friend);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public void insert(Friend friend){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(INITIAL, friend.getInitial());
        initialValues.put(NAME, friend.getName());
        initialValues.put(MAJOR, friend.getMajor());
        initialValues.put(PHONE, friend.getPhone());
        database.insert(DATABASE_TABLE, null, initialValues);
    }

    public void delete(int id){
        database.delete(TABLE_FRIEND, _ID + " = '" + id + "'", null);
    }
}
