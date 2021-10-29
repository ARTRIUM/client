package com.example.everytranslation.data.service;

import com.example.everytranslation.data.model.*
import com.example.everytranslation.data.service.util.rest.RestApiService;
import com.example.everytranslation.data.service.util.rest.RestApiServiceCallback
import java.util.function.Consumer

class UserApiService(private val restApiService: RestApiService) {

    suspend fun requestVerifyCode(email : String) : ApiResult<String>{
        return requestVerifyCode(email)
    }

    suspend fun requestVerify(verifyEmailDTO: VerifyEmailDTO) : ApiResult<String>{
        return restApiService.requestVerify(verifyEmailDTO)
    }

    suspend fun signUp(signUpForm: SignUpForm) : ApiResult<String>{
        return restApiService.signUp(signUpForm)
    }

    suspend fun login(signInForm: SignInForm) : ApiResult<LoginSuccessDTO>{
        return restApiService.login(signInForm)
    }

    companion object{
        val instance = UserApiService(RestApiService.instance)
    }
}
