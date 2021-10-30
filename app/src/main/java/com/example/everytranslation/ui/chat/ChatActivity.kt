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
import com.example.everytranslation.db.dto.ChatRoom
import com.example.everytranslation.db.dto.Message
import com.example.everytranslation.db.dto.User
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class ChatActivity : AppCompatActivity(), TextWatcher, View.OnClickListener {

    private val messageApiService = MessageApiService.getNewInstance()

    private lateinit var user : User
    private lateinit var room : ChatRoom

    private lateinit var binding: ActivityChatBinding
    private lateinit var adapter: ChatAdapter
    var changeType = 0
    var s = ""

    private var flag: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)

        user = intent.getParcelableExtra("user")!!
        room = intent.getParcelableExtra("room")!!

        binding.chatText.addTextChangedListener(this)
        adapter = ChatAdapter(user)
        binding.chatContent.adapter = adapter
        binding.chatContent.layoutManager = LinearLayoutManager(this)
        binding.chatContent.setHasFixedSize(false)

        binding.chatMenu.setOnClickListener {
            val intent = Intent(this, ChatDrawerActivity::class.java)
            startActivity(intent)
        }

        binding.back.setOnClickListener { // 뒤로가기 버튼
            finish()
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
                messageApiService.sendMessage(user.userId.toInt(),room.roomId, s);
                submit_chat()
            }
        }
    }

    fun submit_chat(){
        //messageApiService.getAllMessages()

        //adapter.addChat(chat)
        binding.chatContent.smoothScrollToPosition(adapter.lst.size-1)
        adapter.notifyItemChanged(adapter.lst.size-1)
        binding.chatText.text = null
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }


    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        this.s = s.toString()


        binding.chatContent.adapter?.notifyDataSetChanged()

    }

    override fun afterTextChanged(s: Editable?) {
    }
}
