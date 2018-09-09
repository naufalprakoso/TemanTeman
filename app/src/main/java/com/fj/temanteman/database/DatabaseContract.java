package com.fj.temanteman.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_FRIEND = "MsTeman";
    public static final class FriendColumns implements BaseColumns {
        public static String INITIAL = "initial";
        public static String NAME = "name";
        public static String MAJOR = "major";
        public static String PHONE = "phone";
    }
}
