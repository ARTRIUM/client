package com.example.everytranslation.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.everytranslation.databinding.ActivityFindPasswordVerifyBinding

class FindPasswordVerifyActivity : AppCompatActivity() {

    val binding by lazy { ActivityFindPasswordVerifyBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.findPasswordVerify.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}