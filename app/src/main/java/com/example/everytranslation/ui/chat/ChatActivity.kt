package com.example.everytranslation.ui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.MainActivity
import com.example.everytranslation.R
import com.example.everytranslation.adapter.ChatAdapter
import com.example.everytranslation.data.ChatDTO
import com.example.everytranslation.data.model.ChatMeDTO
import com.example.everytranslation.data.service.MessageApiService
import com.example.everytranslation.databinding.ActivityChatBinding
import com.example.everytranslation.databinding.ChatDetailBinding
import com.example.everytranslation.db.AppDatabase
import com.example.everytranslation.db.dto.ChatRoom
import com.example.everytranslation.db.dto.Message
import com.example.everytranslation.db.dto.User
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class ChatActivity : AppCompatActivity() {


    private val messageApiService = MessageApiService.getNewInstance()


    private lateinit var user : User
    private lateinit var room : ChatRoom
    private lateinit var viewAdapter : ChatAdapter
    private lateinit var ChatActivityRecycleview : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        user = intent.getParcelableExtra("user")!!
        room = intent.getParcelableExtra("room")!!
        viewAdapter = ChatAdapter(user)

        ChatActivityRecycleview = findViewById(R.id.chat_content)

        val chatMenu = findViewById<Button>(R.id.chat_menu)
        val chatSend = findViewById<Button>(R.id.send)
        val backBtn = findViewById<Button>(R.id.back)
        val chatTextView = findViewById<EditText>(R.id.chat_text)

        ChatActivityRecycleview.adapter=viewAdapter
        ChatActivityRecycleview.layoutManager=LinearLayoutManager(applicationContext)
        ChatActivityRecycleview.setHasFixedSize(true)

        AppDatabase.getInstance(applicationContext).messageDao().getAll(room.roomId).observe(this){
            dataChangeAndScrollToEnd(it)
        }


        chatMenu.setOnClickListener {
            val intent = Intent(this, ChatDrawerActivity::class.java)
            startActivity(intent)
        }

        backBtn.setOnClickListener { // 뒤로가기 버튼
            finish()
        }

        chatSend.setOnClickListener {
            addChat(chatTextView.text.toString())
            chatTextView.setText("")
        }


//        binding.chatMike.setOnClickListener { // 마이크버튼
//            if (flag == 0) {
//                changeImage(R.drawable.ic_mike_off)
//                flag = 1;
//            }
//            else if(flag == 1){
//                changeImage(R.drawable.ic_mike)
//                flag =0;
//            }
//
//        }


    }

    //    private fun changeImage(image: Int) {
//        binding.chatMike.setImageResource(R.drawable.ic_mike_off)
//    }

    fun dataChangeAndScrollToEnd(messages: List<Message>) {
        viewAdapter.setMessages(messages)
        if(viewAdapter.itemCount > 0)
            ChatActivityRecycleview.smoothScrollToPosition(viewAdapter.itemCount - 1)
    }

    fun addChat(content:String){
        val current = System.currentTimeMillis()
        val t_date = Date(current)
        val t_dateFormat = SimpleDateFormat("kk:mm", Locale("ko", "KR"))
        val str_time = t_dateFormat.format(t_date)

        messageApiService.sendMessage(user.userId.toInt(),room.roomId,content);
    }

}
