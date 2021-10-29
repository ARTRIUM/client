package com.example.everytranslation.ui.auth

import android.util.Log
import android.widget.Spinner
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.everytranslation.R
import com.example.everytranslation.data.model.*
import com.example.everytranslation.data.service.UserApiService
import com.example.everytranslation.utils.MyApplication
import com.example.everytranslation.utils.NetworkStatus
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel() {

    //sign up field
    var signupName = ObservableField<String>()
    var signupPassword = ObservableField<String>()
    var signupEmail = ObservableField<String>()
    var permissionCode = ObservableField<String>()
    var signupPhone = ObservableField<String>()
    var signupLanguageRadio = ObservableField<Int>()
    var signUpResponseCode = ""

    // signIn field
    var signInEmail = ObservableField<String>()
    var signInPassword = ObservableField<String>()
    var isSelected = ObservableField<Boolean>()

    //auth listener
    var authSignUpListener: AuthListener? = null
    var authSignInListener: AuthListener? = null
    var networkErrorString = "네트워크 연결을 확인해 주세요."


    // 회원 가입 인증번호 요청
    private val _getResponse : MutableLiveData<ApiResult<String>> = MutableLiveData()
    val getResponse : LiveData<ApiResult<String>> get() = _getResponse
    fun getVerifyCode() = viewModelScope.launch {
        if(NetworkStatus.status){
            Log.d("verifyCode","인증번호 요청 됐다")
            _signUpLoading.postValue(true)
            _getResponse.value = UserApiService.instance.requestVerifyCode(signupEmail.get()!!)
        }else{
            authSignUpListener?.onFailure(networkErrorString,99)
        }
    }


    // 회원가입 인증번호 확인
    private val _verifyPostResponse : MutableLiveData<ApiResult<String>> = MutableLiveData()
    val verifyPostResponse : LiveData<ApiResult<String>> get() = _verifyPostResponse
    fun postVerifyCode() = viewModelScope.launch {
        if(NetworkStatus.status){
            _signUpLoading.postValue(true)
            _verifyPostResponse.value = UserApiService.instance.requestVerify(
                VerifyEmailDTO(signupEmail.get()!!,permissionCode.get()!!)
            )
        }
        else{
            authSignUpListener?.onFailure(networkErrorString,99)
        }
    }

    // 회원가입
    private val _signUpResponse : MutableLiveData<ApiResult<String>> = MutableLiveData()
    val signUpResponse : LiveData<ApiResult<String>> get() = _signUpResponse

    private val _signUpLoading = MutableLiveData<Boolean>()
    val signUpLoading: LiveData<Boolean> get() = _signUpLoading //회원가입, 회원 가입 인증번호 요청, 회원 가입 인증번호 확인 시 사용

    fun postSignUP() = viewModelScope.launch {
        if(NetworkStatus.status){
            _signUpLoading.postValue(true)
            var language = when(signupLanguageRadio.get()){
                R.id.radio_korean -> "KOREAN"
                R.id.radio_english -> "ENGLISH"
                R.id.radio_chinese -> "CHINESE"
                else -> "NULL"
            }
            Log.d("language",language)
            _signUpResponse.value = UserApiService.instance.signUp(
                SignUpForm(signupName.get()!!, signupPassword.get()!!, signupEmail.get()!!, language,signUpResponseCode)
            )

        }
        else{
            authSignUpListener?.onFailure(networkErrorString,99)
        }
    }

    // 로그인
    private val _signInResponse : MutableLiveData<ApiResult<LoginSuccessDTO>> = MutableLiveData()
    val signInResponse : LiveData<ApiResult<LoginSuccessDTO>> = _signInResponse

    private val _signInLoading = MutableLiveData<Boolean>()
    val signInLoading: LiveData<Boolean> get() = _signInLoading

    fun postSignIn() = viewModelScope.launch {
        if(NetworkStatus.status){
            _signInLoading.postValue(true)
            if(isSelected.get()!!){
                MyApplication.prefs.setUserEmail(signInEmail.get()!!)
                MyApplication.prefs.setUserPass(signInPassword.get()!!)
            }
            _signInResponse.value = UserApiService.instance.login(
                SignInForm(signInEmail.get()!!, signInPassword.get()!!)
            )
            _signInLoading.postValue(false)
        }
        else{
            Log.d("networkStatus","in viewmodel " + NetworkStatus.status.toString())
            authSignInListener?.onFailure("네트워크 연결을 확인해 주세요.",99)
        }

    }

    // 로그인 필드 확인
    fun checkSignInField(){
        Log.d("networkStatus","in checkSignInFeild")
        if(signInEmail.get().isNullOrEmpty()){
            authSignInListener?.onFailure("이메일을 입력해주세요",0)
            return
        }
        if(signInPassword.get().isNullOrEmpty()){
            authSignInListener?.onFailure("비밀번호를 입력해주세요",1)
            return
        }

        postSignIn()
    }

    // 회원가입 필드 확인
    fun checkSignUpFeild(){
        if(signupName.get().isNullOrEmpty()){
            authSignUpListener?.onFailure("이름을 입력해주세요", 1)
            return
        }
        if(signupPassword.get().isNullOrEmpty()){
            authSignUpListener?.onFailure("비밀번호를 입력해주세요", 2)
            return
        }
        if(signupEmail.get().isNullOrEmpty()){
            authSignUpListener?.onFailure("이메일을 입력해주세요", 3)
            return
        }
        if(signupPhone.get().isNullOrEmpty()){
            authSignUpListener?.onFailure("전화번호를 입력해주세요", 4)
            return
        }
        if(signupLanguageRadio.get()!=R.id.radio_korean && signupLanguageRadio.get()!=R.id.radio_english && signupLanguageRadio.get()!=R.id.radio_chinese){
            authSignUpListener?.onFailure("언어를 선택해주세요", 5)
            return
        }


        postSignUP()
    }

    fun removeEditText(){
        signInEmail.set("")
        signInPassword.set("")
    }

    fun getSharedPreference(){
        if(!MyApplication.prefs.getUserEmail()!!.equals("") && !MyApplication.prefs.getUserPass()!!.equals("")) {
            signInEmail.set(MyApplication.prefs.getUserEmail()!!)
            signInPassword.set(MyApplication.prefs.getUserPass()!!)
            Log.d("networkStatus","in getSharedPreference")
            checkSignInField()
        }
        else
            return
    }

    fun setSignInLoadingFalse(){
        _signUpLoading.postValue(false)
    }

}