package com.example.everytranslation.data.service.util.rest



import com.example.everytranslation.data.model.*
import com.example.everytranslation.db.dto.ChatRoom
import com.example.everytranslation.db.dto.Friend
import com.example.everytranslation.db.dto.Message
import com.example.everytranslation.data.model.AddFriendDTO
import com.example.everytranslation.db.dto.User

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface RestApiService {

    // User
    @GET("/user/verify/{requestEmail}") suspend fun requestVerifyCode(@Path("requestEmail") email: String) : ApiResult<String>
    @POST("/user/verify") suspend fun requestVerify(@Body verifyEmailDTO : VerifyEmailDTO) : ApiResult<String>
    @POST("/user/signup") suspend fun signUp(@Body signUpForm: SignUpForm) : ApiResult<String>
    @POST("/user/login") suspend fun login(@Body signInForm: SignInForm) : ApiResult<LoginSuccessDTO>

    //친구 추가
    @POST("/friend") fun addFriend(@Body addFriendForm : AddFriendForm) : ApiResult<String>

    // Friend
    @GET("/friends") fun getFriends() : Call<List<Friend>>
    @POST("/friend") fun addFriend(@Body email : AddFriendDTO) : Call<AddFriendSuccessDto>

    // Room
    @POST("/room/create") fun createRoom(@Body createRoomDTO: CreateRoomDTO) : Call<Long>
    @GET("/room/{roomId}") fun getRoom(@Path("roomId") roomId:Int) : Call<ChatRoom>
    @GET("/rooms") fun getRooms() : Call<List<ChatRoom>>

    // Message Api
    @GET("/message/all/{roomId}") fun getMessages(@Path("roomId") roomId : Int) : Call<List<Message>>

    companion object {
        val instance = RestApiServiceGenerator.createService(RestApiService::class.java)
    }
}