package com.example.everytranslation.ui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.MainActivity
import com.example.everytranslation.R
import com.example.everytranslation.data.ChatDTO
import com.example.everytranslation.data.model.ChatMeDTO
import com.example.everytranslation.databinding.ActivityChatBinding
import com.example.everytranslation.databinding.ChatDetailBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class ChatActivity : AppCompatActivity() {


    private lateinit var binding: ActivityChatBinding
    private var flag: Int = 0
    private val data = arrayListOf<ChatMeDTO>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)

        setContentView(R.layout.activity_chat)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.chatContent.adapter = ChatAdapter(data)
        binding.chatContent.layoutManager = LinearLayoutManager(this)

        binding.chatMenu.setOnClickListener {
            val intent = Intent(this, ChatDrawerActivity::class.java)
            startActivity(intent)
        }

        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.chatMike.setOnClickListener {
            if (flag == 0) {
                changeImage(R.drawable.ic_mike_off)
                flag = 1;
            }
            else if(flag == 1){
                changeImage(R.drawable.ic_mike)
                flag =0;
            }

        }
         binding.send.setOnClickListener {
            addChat()
            binding.chatText.setText("")
        }
    }

    private fun changeImage(image: Int) {
        binding.chatMike.setImageResource(R.drawable.ic_mike_off)
    }


    fun addChat(){
        val current = System.currentTimeMillis()
        val t_date = Date(current)
        val t_dateFormat = SimpleDateFormat("kk:mm", Locale("ko", "KR"))
        val str_time = t_dateFormat.format(t_date)
        val chat = ChatMeDTO(binding.chatText.text.toString(), str_time, false)
        data.add(chat)
        binding.chatContent.adapter?.notifyDataSetChanged()
    }

}
