package com.example.everytranslation.data.request

import com.example.everytranslation.data.model.ApiResult
import com.example.everytranslation.data.model.VerifyEmailDTO
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeService {
    // 미팅 시작
    @POST("/room/create/{userId}")
    suspend fun createRoom(@Path("userId") userId: Long) : ApiResult<String>
}