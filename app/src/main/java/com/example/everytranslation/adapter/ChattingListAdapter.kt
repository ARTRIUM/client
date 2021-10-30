package com.example.everytranslation.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.R
import com.example.everytranslation.db.dto.ChatRoom
import com.example.everytranslation.db.dto.Message
import com.example.everytranslation.db.dto.User
import com.example.everytranslation.ui.chat.ChatActivity
import java.lang.IllegalArgumentException
import java.util.ArrayList
import java.util.HashMap
import java.util.logging.Logger

class ChattingListAdapter(val context: Context, val user : User, val activity: Activity) : RecyclerView.Adapter<ChattingListAdapter.Holder>() {

    private val logger = Logger.getLogger(ChattingListAdapter::class.java.name)

    private val roomPositionTable = HashMap<Int,Int>()
    private var roomList = ArrayList<ChatRoom>()

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var roomId = -1
        val roomnameText = itemView?.findViewById<TextView>(R.id.chat_detail_name)
        val curmessageText = itemView?.findViewById<TextView>(R.id.chat_detail_content)
        val recenttimeText = itemView?.findViewById<TextView>(R.id.chat_detail_time)

        fun bind(chatroom: ChatRoom, context: Context) {
            roomId = chatroom.roomId
            roomnameText?.text = chatroom.roomName
            curmessageText?.text = chatroom.curMessage
            recenttimeText?.text = chatroom.recentTime
        }

        fun bind(curMessage:String, time:String){
            curmessageText?.text = curMessage
            recenttimeText?.text = time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_detail, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(roomList[position], context)
        holder?.itemView.setOnClickListener{
            val intent = Intent(activity, ChatActivity::class.java)
            intent.putExtra("user", user)
            intent.putExtra("room", roomList[position])

            context.startActivity(intent)
        }
    }

    // this used for data set changed
    override fun onBindViewHolder(holder: Holder, position: Int, payloads: MutableList<Any>) {
        if(payloads.isEmpty()){
            onBindViewHolder(holder,position)
            return;
        }

        val message = payloads.get(0) as Message
        holder?.bind(message.content, message.writtenAt)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    fun notifyItemChangedBy(roomId:Int, message:Message){
        if(!roomPositionTable.containsKey(roomId))
            throw IllegalArgumentException("adapter does not have roomId")

        notifyItemChanged(roomPositionTable.get(roomId)!!, message)
    }

    fun setRooms(rooms : List<ChatRoom>){
        this.roomList.clear()
        this.roomList = rooms as ArrayList<ChatRoom>
        for(i in 0 until roomList.size){
            roomPositionTable[roomList[i].roomId] = i
        }
        notifyDataSetChanged()
    }
}