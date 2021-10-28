package com.example.everytranslation.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.everytranslation.R
import com.example.everytranslation.databinding.ActivityChatDrawerBinding

class ChatDrawerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_drawer)


    }
}