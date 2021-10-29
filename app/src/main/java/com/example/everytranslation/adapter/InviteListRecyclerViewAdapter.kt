package com.example.everytranslation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.R
import com.example.everytranslation.data.FriendDTO
import com.example.everytranslation.databinding.ActivityAddFriendBinding.inflate
import com.example.everytranslation.db.dto.Friend

class InviteListRecyclerViewAdapter(val context: Context, val friendList: ArrayList<Friend>, val inviteList: ArrayList<Friend>)
    :RecyclerView.Adapter<InviteListRecyclerViewAdapter.Holder>(){


        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            val checkBox = itemView?.findViewById<CheckBox>(R.id.checkbox_invite_friend)

            fun bind(friend : Friend, context: Context){

               checkBox?.setOnCheckedChangeListener { _, isChecked ->
                    if(isChecked){
                        Log.d("체크됨", "체크됨")
                        inviteList.add(friend)
                    }else{
                        Log.d("체크XX", "체크XXXXXXXXXXXXX")
                        inviteList.remove(friend)
                    }
                }

            }

        }

        fun addInvite(item: Friend) {
            friendList.add(item)
            notifyItemInserted(friendList.size-1);
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val inviteDetailBinding = LayoutInflater.from(context).inflate(R.layout.invite_detail, parent, false)
            return Holder(inviteDetailBinding)
        }

        override fun getItemCount(): Int {
            return inviteList.size;
        }

        override fun onBindViewHolder(holder:Holder, position: Int) { // 데이터를 뷰 홀더와 연결
            holder?.bind(friendList[position], context)
        }

}
