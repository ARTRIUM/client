package com.example.everytranslation.data.repository

import com.example.everytranslation.data.model.AddFriendForm
import com.example.everytranslation.data.request.ApiRequestFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FriendRepository {

    //친구 추가
    suspend fun addFriend(addFriendForm : AddFriendForm) = withContext(Dispatchers.IO){
    ApiRequestFactory.userService.addFriend(addFriendForm)
    }
}