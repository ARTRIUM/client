package com.example.everytranslation.adapter;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.R
import com.example.everytranslation.data.FriendDTO
import com.example.everytranslation.databinding.FriendDetailBinding
import com.example.everytranslation.db.dto.Friend

class FriendListViewAdapter (val context: Context, val friends: ArrayList<Friend>)
    : RecyclerView.Adapter<FriendListViewAdapter.Holder>(){

    inner class Holder(itemView: View?): RecyclerView.ViewHolder(itemView!!){

        val name : TextView? = itemView?.findViewById(R.id.friend_name);
        val national = itemView?.findViewById<Button>(R.id.friend_nationality);

        fun bind(friend:Friend,context:Context){
            name?.text=friend.name
        }


    }

    fun setFriend(item : Friend){
        friends.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val friendDetailBinding = LayoutInflater.from(parent.context).inflate(R.layout.friend_detail,parent,false)
        return Holder(friendDetailBinding)
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(friends[position],context)
    }



}
