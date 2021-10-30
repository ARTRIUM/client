package com.example.everytranslation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.R
import com.example.everytranslation.data.model.ChatMeDTO
import com.example.everytranslation.databinding.ChatMeBinding
import com.example.everytranslation.databinding.ChatOtherBinding
import com.example.everytranslation.db.dto.Message
import com.example.everytranslation.db.dto.User
import java.lang.RuntimeException

class ChatAdapter(val user : User) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val lst = mutableListOf<Message>()
    val LEFT_TALK = 0   // 타인
    val RIGHT_TALK = 1  // 본인

    private lateinit var chatMeBinding: ChatMeBinding       // right
    private lateinit var chatOtherBinding: ChatOtherBinding // left

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            LEFT_TALK -> {
                chatOtherBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.chat_other, parent, false)
                LeftViewHolder(chatOtherBinding)
            }
            RIGHT_TALK -> {
                chatMeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.chat_me, parent, false)
                RightViewHolder(chatMeBinding)
            }
            else -> {
                throw RuntimeException("알 수 없는 viewtype error")
            }
        }

    }

    override fun getItemCount(): Int {
        return lst.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LeftViewHolder) {
            holder.binding.textGchatMessageOther.text = lst[position].content
            holder.binding.textGchatTimestampOther.text = lst[position].writtenAt.substring(11)
            holder.binding.textGchatUserOther.text = lst[position].writtenBy

        }
        else if (holder is RightViewHolder) {
            holder.binding.textGchatMessageMe.text = lst[position].content
            holder.binding.textGchatTimestampMe.text = lst[position].writtenAt.substring(11)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (user.userId.toInt() == this.lst[position].userId)
            return RIGHT_TALK
        return LEFT_TALK
    }

    inner class LeftViewHolder(val binding : ChatOtherBinding) : RecyclerView.ViewHolder(binding.root){ }
    inner class RightViewHolder(val binding : ChatMeBinding) : RecyclerView.ViewHolder(binding.root){ }

    fun setMessages(messages: List<Message>) {
        this.lst.clear()
        this.lst.addAll(messages)

        notifyDataSetChanged()
    }



}