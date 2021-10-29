package com.example.everytranslation.db.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "USER")
data class User (
    @SerializedName("userId")
    @PrimaryKey
    val userId : Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("password")
    val password : String?,

    @SerializedName("email")
    val email : String,

    @SerializedName("phoneNum")
    val phoneNum : String,

    @SerializedName("language")
    val language : String


) : Parcelable