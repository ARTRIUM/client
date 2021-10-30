package com.example.everytranslation.data.model

import com.google.gson.annotations.SerializedName

data class EventInvite(

    @SerializedName("roomName")
    val roomName: String,


    @SerializedName("roomId")
    val roomId: Int
)