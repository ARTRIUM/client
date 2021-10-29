package com.example.everytranslation.data.service.util.rest


import com.example.everytranslation.data.model.AddFriendDTO
import com.example.everytranslation.db.dto.Friend
import com.example.everytranslation.db.dto.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface RestApiService {

    // Friend API
    @POST("/api/friend")
    fun addFriend(@Body email : AddFriendDTO) : Call<String>

    companion object {
        val instance = RestApiServiceGenerator.createService(RestApiService::class.java)
    }
}