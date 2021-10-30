package com.example.everytranslation.data.service.util.rest


import com.example.everytranslation.data.model.*
import com.example.everytranslation.db.dto.Friend
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


    // Room
    @POST("/room/create") fun createRoom(@Body createRoomDTO: CreateRoomDTO) : Call<String>


    companion object {
        val instance = RestApiServiceGenerator.createService(RestApiService::class.java)
    }
}