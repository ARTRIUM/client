package com.example.everytranslation.data.request

import com.example.everytranslation.data.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    // 회원 가입 인증 번호 전송
    @GET("/user/verify/{requestEmail}")
    suspend fun requestVerifyCode(@Path("requestEmail") email: String) : ApiResult<String>

    // 이메일 인증번호 확인
    @POST("/user/verify")
    suspend fun requestVerify(@Body verifyEmailDTO : VerifyEmailDTO) : ApiResult<String>

    @POST("/user/signup")
    suspend fun signUp(@Body signUpForm: SignUpForm) : ApiResult<String>

    @POST("/user/login")
    suspend fun login(@Body signInForm: SignInForm) : ApiResult<LoginSuccessDTO>

    //친구 추가
    @POST("/api/friend")
    suspend fun addFriend(@Body addFriendForm : AddFriendForm) : ApiResult<String>
}