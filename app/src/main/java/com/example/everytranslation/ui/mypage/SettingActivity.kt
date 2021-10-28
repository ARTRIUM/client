package com.example.everytranslation.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.everytranslation.databinding.ActivitySettingBinding
import com.example.everytranslation.ui.activity.MypageActivity

class SettingActivity : AppCompatActivity() {
    val binding by lazy { ActivitySettingBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backToMypage.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }
    }
}