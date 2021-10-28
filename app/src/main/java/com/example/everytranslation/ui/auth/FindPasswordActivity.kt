package com.example.everytranslation.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.everytranslation.databinding.ActivityFindPasswordBinding

class FindPasswordActivity : AppCompatActivity() {

    val binding by lazy { ActivityFindPasswordBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.sendEmail.setOnClickListener {
            val intent = Intent(this, FindPasswordVerifyActivity::class.java)
            startActivity(intent)
        }
    }
}