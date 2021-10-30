package com.example.hanium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.everytranslation.MainActivity
import com.example.everytranslation.R
import com.example.everytranslation.databinding.ActivityLoginBinding
import com.example.everytranslation.db.AppDatabase
import com.example.everytranslation.ui.auth.AuthListener
import com.example.everytranslation.ui.auth.AuthViewModel
import com.example.everytranslation.ui.auth.AuthViewModelFactory
import com.example.everytranslation.ui.auth.FindPasswordActivity
import com.example.everytranslation.utils.MyApplication
import com.example.everytranslation.utils.NetworkConnection
import com.example.everytranslation.utils.NetworkStatus
import com.example.everytranslation.utils.toast
import com.example.everytranslation.db.dto.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SignInActivity : AppCompatActivity(),AuthListener {

    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel : AuthViewModel
    lateinit var viewModelFactory: AuthViewModelFactory

    //val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initViewModel()
        val connection = NetworkConnection(applicationContext)
        connection.observe(this){ isConnected ->
            if (isConnected) NetworkStatus.status = true
            else NetworkStatus.status = false
        }

        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.findPasswordBtn.setOnClickListener {
            val intent = Intent(this, FindPasswordActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initViewModel(){
        viewModelFactory = AuthViewModelFactory()
        viewModel = ViewModelProvider(this,viewModelFactory).get(AuthViewModel::class.java)
        viewModel.authSignInListener = this
        viewModel.isSelected.set(false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.signInResponse.observe(this){
            if(it.success){
                MyApplication.prefs.setUserId(it.response.userId)
                MyApplication.prefs.setUserName(it.response.userName)
                MyApplication.prefs.setUserLanguage(it.response.userLanguage)

                Thread{
                    val user = User(it.response.userId, it.response.userName, binding.loginPassword.text.toString(), binding.loginEmail.text.toString(), "", it.response.userLanguage)
                    AppDatabase.getInstance(applicationContext).userDao().insert(user)
                }.start()

                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("user", User(it.response.userId, it.response.userName, MyApplication.prefs.getUserPass(), MyApplication.prefs.getUserEmail(), "", it.response.userLanguage))
                startActivity(intent)
                finish()
            }
            else{
                toast(it.error.message)
                viewModel.removeEditText()
            }
        }
    }

    override fun onStarted() {}
    override fun onSuccess() {}

    override fun onFailure(message: String, type: Int) {
        when(type){
            0 -> {
                binding.loginEmail.requestFocus()
            }
            1 -> {
                binding.loginPassword.requestFocus()
            }
        }
        toast(message)
    }
}
