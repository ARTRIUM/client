package com.example.everytranslation.data.model

data class ChatMeDTO (
    val text: String,
    val time: String,
    val isDone: Boolean = false
)