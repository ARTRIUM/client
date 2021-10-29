package com.example.everytranslation.db.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "CHAT_ROOM")
data class ChatRoom(

    @SerializedName("roomId")
    @PrimaryKey
    var roomId: Int,

    @SerializedName("roomName")
    var roomName: String,

    @SerializedName("curMessage")
    @Ignore
    var curMessage: String,

    @SerializedName("recentTime")
    @Ignore
    var recentTime: String

):Parcelable {
    constructor() : this(-1, "", "", "")
}