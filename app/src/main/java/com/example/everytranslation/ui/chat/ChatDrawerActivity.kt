package com.example.everytranslation.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.R
import com.example.everytranslation.data.FriendDTO
import com.example.everytranslation.databinding.ActivityChatDrawerBinding
import com.example.everytranslation.databinding.InviteDetailBinding

class ChatDrawerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatDrawerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_drawer)
        setContentView(binding.root)

        binding.lifecycleOwner=this
        binding.invitelistfragmentRecyclerview.adapter = InvitelistRecyclerViewAdapter()
        binding.invitelistfragmentRecyclerview.layoutManager = LinearLayoutManager(this)

    }
    inner class InvitelistRecyclerViewAdapter : RecyclerView.Adapter<InvitelistRecyclerViewAdapter.Holder>() {
        var inviteDTOs: ArrayList<FriendDTO> = arrayListOf()

        init {
            // reset
            inviteDTOs.clear()

            // friends data list loading
            inviteDTOs.add(FriendDTO("people1", "url1"))
            inviteDTOs.add(FriendDTO("people2", "url2"))
            inviteDTOs.add(FriendDTO("people3", "url3"))
            inviteDTOs.add(FriendDTO("people4", "url4"))
            inviteDTOs.add(FriendDTO("people5", "url5"))

            notifyDataSetChanged()
        }
        inner class Holder(val inviteDetailBinding: InviteDetailBinding) : RecyclerView.ViewHolder(inviteDetailBinding.root) {
            init {
//                inviteDetailBinding.root.setOnClickListener {
//                    Toast.makeText(inviteDetailBinding.root.context, "클릭된 아이템 = ${inviteDetailBinding.inviteName.text}", Toast.LENGTH_LONG).show()
//                }
                inviteDetailBinding.checkboxInviteFriend.setOnClickListener{
                }
            }

            fun setInvite(item: FriendDTO) {
                inviteDetailBinding.inviteName.text = item.name
                // friendDetailBinding.friendImageUrl.text = item.imageUrl
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder { // ViewHolder를 새로 만들어야 할때 호출
            val inviteDetailBinding = InviteDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(inviteDetailBinding)
        }

        override fun getItemCount(): Int {
            return inviteDTOs.size
        }

        override fun onBindViewHolder(holder:Holder, position: Int) { // 데이터를 뷰 홀더와 연결
            var item = inviteDTOs.get(position)
            holder.setInvite(item)
        }




    }
}