package com.example.everytranslation.data.service

import com.example.everytranslation.data.model.CreateRoomDTO
import com.example.everytranslation.data.model.EventInvite
import com.example.everytranslation.data.service.util.rest.RestApiService
import com.example.everytranslation.data.service.util.rest.RestApiServiceCallback
import com.example.everytranslation.data.service.util.stomp.MyStompClient
import com.example.everytranslation.db.dto.ChatRoom

import com.google.gson.Gson
import java.util.function.Consumer

class RoomApiService(private val restApiService: RestApiService,private val myStompService: MyStompClient) {

    fun getRooms(callback : Consumer<List<ChatRoom>>){
        restApiService.getRooms().enqueue(RestApiServiceCallback(callback))
    }

    fun getRoom(roomId : Int,callback : Consumer<ChatRoom>){
        restApiService.getRoom(roomId).enqueue(RestApiServiceCallback(callback))
    }

    fun createRoom(createRoomDTO: CreateRoomDTO ,callback : Consumer<Long>){
        restApiService.createRoom(createRoomDTO).enqueue(RestApiServiceCallback(callback))
    }

    fun sendGreetingEvent(toId:Long, eventInvite: EventInvite){
        myStompService.send("/pub/event/sub/$toId", Gson().toJson(eventInvite)){
        }
    }

    companion object{
        val instance = RoomApiService(RestApiService.instance, MyStompClient.instance)
    }
}