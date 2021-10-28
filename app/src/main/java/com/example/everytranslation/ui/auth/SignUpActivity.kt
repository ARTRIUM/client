package com.example.hanium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.everytranslation.R
import com.example.everytranslation.data.repository.AuthRepository
import com.example.everytranslation.databinding.ActivitySignUpBinding
import com.example.everytranslation.ui.auth.AuthListener
import com.example.everytranslation.ui.auth.AuthViewModel
import com.example.everytranslation.ui.auth.AuthViewModelFactory
import com.example.everytranslation.utils.NetworkConnection
import com.example.everytranslation.utils.NetworkStatus
import com.example.everytranslation.utils.toast

class SignUpActivity : AppCompatActivity() , AuthListener{

    private lateinit var binding: ActivitySignUpBinding
    lateinit var viewModel : AuthViewModel
    lateinit var viewModelFactory: AuthViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        val connection = NetworkConnection(applicationContext)
        connection.observe(this){ isConnected ->
            if (isConnected) NetworkStatus.status = true
            else NetworkStatus.status = false
        }

        initViewModel()
    }

    private fun initViewModel(){
        viewModelFactory = AuthViewModelFactory(AuthRepository())
        viewModel = ViewModelProvider(this,viewModelFactory).get(AuthViewModel::class.java)
        viewModel.authSignUpListener = this
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.getResponse.observe(this) {
            viewModel.setSignInLoadingFalse()
            toast(it.response)
            binding.signUpVerifyBtn.isEnabled = true
            binding.signUpVerifyText.isEnabled = true
            binding.signUpVerifyText.isFocusable = true
        }

        viewModel.verifyPostResponse.observe(this){
            viewModel.setSignInLoadingFalse()
            if(it.success){
                toast("인증 성공")
                viewModel.signUpResponseCode = it.response
                
            }
            else{
                toast("인증 실패")
            }
        }

        viewModel.signUpResponse.observe(this) {
            viewModel.setSignInLoadingFalse()
            if (it.success) {
                toast("회원 가입 완료")
                finish()
            } else {
                toast(it.error.message)
            }
        }

    }

  
    override fun onStarted() {}

    override fun onSuccess() {}

    override fun onFailure(message: String, type: Int) {
        when(type) {
            1 -> binding.signUpName.requestFocus()
            2 -> binding.signUpPassword.requestFocus()
            3 -> binding.signUpEmailText.requestFocus()
            4 -> binding.signUpPhoneNumber.requestFocus()
            5 -> binding.radioGroup.requestFocus()
        }
        toast(message)
    }
}