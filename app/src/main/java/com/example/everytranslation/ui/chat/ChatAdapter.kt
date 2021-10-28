package com.example.everytranslation.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.R
import com.example.everytranslation.data.model.ChatMeDTO
import com.example.everytranslation.databinding.ChatMeBinding

class ChatAdapter(private val dataSet: List<ChatMeDTO>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    class ChatViewHolder(val binding : ChatMeBinding) : RecyclerView.ViewHolder(binding.root) {
        val text: TextView
            get(){
                TODO()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_me, parent, false)
        return ChatViewHolder(ChatMeBinding.bind(view))

    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val listPosition = dataSet[position]
        holder.binding.textGchatMessageMe.text = listPosition.text
        holder.binding.textGchatTimestampMe.text = listPosition.time
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}