package com.example.everytranslation.data.repository

import com.example.everytranslation.data.request.ApiRequestFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository {
    // 미팅 시작
    suspend fun startMeet(userId : Long) = withContext(Dispatchers.IO){
        ApiRequestFactory.homeService.createRoom(userId)
    }
}