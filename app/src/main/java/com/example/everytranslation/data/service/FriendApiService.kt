package com.example.everytranslation.data.service

import android.util.Log
import com.example.everytranslation.data.model.AddFriendDTO
import com.example.everytranslation.db.dto.Friend
import com.example.everytranslation.data.service.util.rest.RestApiService
import com.example.everytranslation.data.service.util.rest.RestApiServiceCallback
import java.util.function.Consumer

class FriendApiService(private val restApiService: RestApiService) {

    fun addFriend(email : AddFriendDTO, callback : Consumer<String>) {
        Log.d("service 이메일", email.email)
        restApiService.addFriend(email).enqueue(RestApiServiceCallback(callback))
    }

    fun getFriendAll(callback : Consumer<List<Friend>>){
        restApiService.getFriends().enqueue(RestApiServiceCallback(callback))

    }

    companion object{
        val instance = FriendApiService(RestApiService.instance)
    }
}