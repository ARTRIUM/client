package com.example.everytranslation.ui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.MainActivity
import com.example.everytranslation.R
import com.example.everytranslation.data.ChatDTO
import com.example.everytranslation.data.model.ChatMeDTO
import com.example.everytranslation.data.service.MessageApiService
import com.example.everytranslation.databinding.ActivityChatBinding
import com.example.everytranslation.databinding.ChatDetailBinding
import com.example.everytranslation.db.dto.Message
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class ChatActivity : AppCompatActivity(), TextWatcher, View.OnClickListener {

    private val messageApiService = MessageApiService.getNewInstance()

    private lateinit var binding: ActivityChatBinding
    private lateinit var adapter: ChatAdapter
    var changeType = false
    var s = ""

    private var flag: Int = 0
    //private val data = arrayListOf<ChatMeDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)

        binding.chatText.addTextChangedListener(this)
        adapter = ChatAdapter()
        binding.chatContent.adapter = adapter
        binding.chatContent.layoutManager = LinearLayoutManager(this)
        binding.chatContent.setHasFixedSize(false)

        binding.chatMenu.setOnClickListener {
            val intent = Intent(this, ChatDrawerActivity::class.java)
            startActivity(intent)
        }

        binding.back.setOnClickListener { // 뒤로가기 버튼
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.chatMike.setOnClickListener { // 마이크버튼
            if (flag == 0) {
                changeImage(R.drawable.ic_mike_off)
                flag = 1;
            } else if (flag == 1) {
                changeImage(R.drawable.ic_mike)
                flag = 0;
            }

        }
    }

    private fun changeImage(image: Int) {
        binding.chatMike.setImageResource(R.drawable.ic_mike_off)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.send -> {
                messageApiService.sendMessage(1,1,binding.chatText.text.toString());
                //submit_chat()
            }
        }
    }

//    fun submit_chat(){
//        val current = System.currentTimeMillis()
//        val t_date = Date(current)
//        val t_dateFormat = SimpleDateFormat("kk:mm", Locale("ko", "KR"))
//        val str_time = t_dateFormat.format(t_date)
//
//        val chat = ChatMeDTO(s, str_time, false)
//        data.add(chat)
//
//        val chat = if (changeType) {
//            Message(1, 1, 1, )
//            messageApiService.sendMessage(1,1,binding.chatText.text.toString());
//
//        } else {
//
//        }
//
//        adapter.addChat(chat)
//        binding.chatContent.smoothScrollToPosition(adapter.lst.size-1)
//        adapter.notifyItemChanged(adapter.lst.size-1)
//        changeType = ! changeType
//        binding.chatText.text = null
//    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        this.s = s.toString()
    }

    override fun afterTextChanged(s: Editable?) {
    }
}
