package com.example.everytranslation.db.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Friend (
    @SerializedName("userId")
    val userId : Long,

    @SerializedName("name")
    val name : String,

    @SerializedName("language")
    val language : String,

) : Parcelable