package com.example.everytranslation.data

data class ChatDTO(
    var messageId: Long,
    var userId : Long,
    var roomId : Long,
    var message : String,
    var language: String,
    var writtenAt : String,
    var writtenDy : String
)
