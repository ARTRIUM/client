package com.example.everytranslation.data.model

import com.google.gson.annotations.SerializedName

data class ApiError(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)
