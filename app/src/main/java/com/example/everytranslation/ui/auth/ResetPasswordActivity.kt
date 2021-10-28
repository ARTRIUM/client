package com.example.everytranslation.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.everytranslation.databinding.ActivityResetPasswordBinding
import com.example.hanium.SignInActivity

class ResetPasswordActivity : AppCompatActivity() {

    val binding by lazy {ActivityResetPasswordBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.resetPasswordBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}