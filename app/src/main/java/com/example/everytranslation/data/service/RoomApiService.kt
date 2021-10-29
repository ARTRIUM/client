package com.example.everytranslation.data.service

import com.example.everytranslation.data.model.CreateRoomDTO
import com.example.everytranslation.data.service.util.rest.RestApiService
import com.example.everytranslation.data.service.util.rest.RestApiServiceCallback
import com.example.everytranslation.db.dto.Friend
import java.util.function.Consumer

class RoomApiService(private val restApiService: RestApiService) {

    fun createRoom(createRoomDTO: CreateRoomDTO ,callback : Consumer<String>){
        restApiService.createRoom(createRoomDTO).enqueue(RestApiServiceCallback(callback))
    }

    companion object{
        val instance = RoomApiService(RestApiService.instance)
    }
}