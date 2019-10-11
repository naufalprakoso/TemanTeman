package com.fj.temanteman.database

import android.provider.BaseColumns

object DatabaseContract {

    internal var TABLE_FRIEND = "MsTeman"

    class FriendColumns : BaseColumns {
        companion object {
            const val ID = "id"
            const val INITIAL = "initial"
            const val NAME = "name"
            const val MAJOR = "major"
            const val PHONE = "phone"
        }
    }
}
