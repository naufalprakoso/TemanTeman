package com.fj.temanteman.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Friend(
    var id: Int = 0,
    var name: String = "",
    var major: String = "",
    var initial: String = "",
    var phone: String = ""
) : Parcelable 
