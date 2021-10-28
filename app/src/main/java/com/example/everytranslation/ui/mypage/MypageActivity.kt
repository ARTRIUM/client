package com.example.everytranslation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.everytranslation.MainActivity
import com.example.everytranslation.databinding.ActivityMypageBinding
import com.example.everytranslation.ui.mypage.ChangePasswordActivity
import com.example.everytranslation.ui.mypage.SettingActivity
import com.example.hanium.SignInActivity

class MypageActivity : AppCompatActivity() {
    val binding by lazy {ActivityMypageBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backToMainBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.settingBtn.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        binding.toChangePasswordBtn.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        binding.toLogoutBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}