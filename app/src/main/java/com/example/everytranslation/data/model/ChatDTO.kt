package com.example.everytranslation.data

import com.example.everytranslation.data.model.UserLanguage

data class ChatDTO(

    var messageId : Long? = null,
    var roomId : Long? =null,
    var userId : Long? = null,
    var message : String? = null,
    var writtenAt : String? = null,
    var writtenBy : String? =null,
    var language: UserLanguage? = null
)
