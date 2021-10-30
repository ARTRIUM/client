package com.example.everytranslation.data.service

import com.example.everytranslation.data.service.util.rest.RestApiService
import com.example.everytranslation.data.service.util.rest.RestApiServiceCallback
import com.example.everytranslation.db.dto.Friend
import java.util.function.Consumer


class FriendApiService(private val restApiService: RestApiService) {

    fun getFriendAll(callback : Consumer<List<Friend>>){
        restApiService.getFriends().enqueue(RestApiServiceCallback(callback))
    }

    companion object{
        val instance = FriendApiService(RestApiService.instance)
    }
}