package com.example.everytranslation.ui.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.R
import com.example.everytranslation.adapter.InviteListRecyclerViewAdapter
import com.example.everytranslation.data.FriendDTO
import com.example.everytranslation.data.model.CreateRoomDTO
import com.example.everytranslation.data.model.UserLanguage
import com.example.everytranslation.data.service.FriendApiService
import com.example.everytranslation.data.service.RoomApiService
import com.example.everytranslation.data.service.util.rest.RestApiService.Companion.instance
import com.example.everytranslation.databinding.ActivityChatDrawerBinding
import com.example.everytranslation.databinding.InviteDetailBinding
import com.example.everytranslation.db.dto.Friend
import java.util.stream.Collector
import java.util.stream.Collectors.toList
import kotlin.streams.toList

class ChatDrawerActivity : AppCompatActivity() {


    private var friendList = ArrayList<Friend>()
    private var inviteList = ArrayList<Friend>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_drawer)

        var viewAdapter = InviteListRecyclerViewAdapter(applicationContext,friendList,inviteList)

        val chatNameText = findViewById<TextView>(R.id.chatNameText)
        val btn_make = findViewById<Button>(R.id.createRoom)
        val inviteListFragmentRecyclerview = findViewById<RecyclerView>(R.id.invitelistfragment_recyclerview)

        inviteListFragmentRecyclerview.adapter = viewAdapter
        inviteListFragmentRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        inviteListFragmentRecyclerview.setHasFixedSize(true)



        FriendApiService.instance.getFriendAll(){
            for(friend in it){
                viewAdapter.addInvite(friend)
            }
        }


        btn_make.setOnClickListener {
            Log.d("확인 버튼 클릭", "확인 버튼 클릭")
            val inviteUserIdList = inviteList.stream()
                .map { o -> o.userId }
                .toList()


            val createRoom = CreateRoomDTO(inviteUserIdList, chatNameText.text.toString())
            RoomApiService.instance.createRoom(createRoom){ roomId ->
                Log.d("inviteList 회전 시작", "inviteList 회전 시작")

                    //val eventInvite = EventInvite(input_roomname_text.text.toString(), user.name, roomId)


                    //RoomApiService.instance.sendGreetingEvent(user.userId, invited.userId.toInt(), eventInvite)
                }
                //RoomApiService.instance.sendGreetingEvent(user.userId, user.userId, eventInvite)


//                RoomApiService.instance.getRoom(roomId){
//                    Log.d("터지니?", it.toString())
//
//                    val intent = Intent(activity, MessageChatActivity::class.java)
//                    intent.putExtra("user", user)
//                    intent.putExtra("room", it)
//                    startActivity(intent)
//                }

            }
        }


}







