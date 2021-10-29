package com.example.everytranslation.data

import com.example.everytranslation.data.model.UserLanguage

data class ChatDTO(


    var userId : Long? = null,
    var userName : String? = null,
    var chatId : Long? = null,
    var message : String? = null,
    var language: UserLanguage? = null
)
