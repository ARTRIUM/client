package com.example.everytranslation.data.repository

import com.example.everytranslation.data.model.SignInForm
import com.example.everytranslation.data.model.SignUpForm
import com.example.everytranslation.data.model.VerifyEmailDTO
import com.example.everytranslation.data.request.ApiRequestFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {

    //이메일 인증번호
    suspend fun requestVerifyCode(email : String) = withContext(Dispatchers.IO){
        ApiRequestFactory.userService.requestVerifyCode(email)
    }
    //인증번호 확인
    suspend fun requestVerify(verifyEmailDTO: VerifyEmailDTO) = withContext(Dispatchers.IO){
        ApiRequestFactory.userService.requestVerify(verifyEmailDTO)
    }

    //회원가입
    suspend fun requestSignUp(signUpForm: SignUpForm) = withContext(Dispatchers.IO) {
        ApiRequestFactory.userService.signUp(signUpForm)
    }

    suspend fun requestSignIn(signInForm: SignInForm) = withContext(Dispatchers.IO) {
        ApiRequestFactory.userService.login(signInForm)
    }
}