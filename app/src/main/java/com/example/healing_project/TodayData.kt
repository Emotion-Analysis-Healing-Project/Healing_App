package com.example.healing_project

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodayData(
    val image : Int,
    val time : String
) : Parcelable
