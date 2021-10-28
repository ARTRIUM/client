package com.example.everytranslation.ui.navigation.Chatting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.data.ChatDTO
import com.example.everytranslation.databinding.ChatDetailBinding
import com.example.everytranslation.databinding.FragmentChattingListBinding
import com.example.everytranslation.ui.chat.ChatActivity
import com.example.everytranslation.ui.chat.ChatTranslationActivity
import com.example.everytranslation.ui.navigation.friend.AddFriendActivity

class ChattingListFragment : Fragment() {

    companion object{
        fun newInstance() : ChattingListFragment {
            return ChattingListFragment()
        }
    }

    lateinit var fragmentbinding: FragmentChattingListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        fragmentbinding = FragmentChattingListBinding.inflate(inflater, container, false)

        fragmentbinding.chattinglistfragmentRecyclerview.adapter = ChattingListRecyclerviewAdapter()
        fragmentbinding.chattinglistfragmentRecyclerview.layoutManager = LinearLayoutManager(activity)

                fragmentbinding.chatlistAddFriends.setOnClickListener{
            val intent = Intent(activity, ChatActivity::class.java )
            startActivity(intent)
        }

        return fragmentbinding.root
    }

    inner class ChattingListRecyclerviewAdapter : RecyclerView.Adapter<ChattingListRecyclerviewAdapter.Holder>(){
        var chatDTOs : ArrayList<ChatDTO> = arrayListOf()
        init{
            // reset
            chatDTOs.clear()

            // friends data list loading
            chatDTOs.add(ChatDTO("people1","회의 시작할까요?","url1"))
            chatDTOs.add(ChatDTO("people2","bbb","url2"))
            chatDTOs.add(ChatDTO("people3","ccc","url3"))
            chatDTOs.add(ChatDTO("people4","ddd","url4"))
            chatDTOs.add(ChatDTO("people5","eee","url5"))
            chatDTOs.add(ChatDTO("people6","fff","url6"))

            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            var chatDetailBinding = ChatDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return Holder(chatDetailBinding)
        }

        override fun getItemCount(): Int {
            return chatDTOs.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            var item = chatDTOs.get(position)
            holder.setChat(item)
        }

        inner class Holder(val chatDetailBinding: ChatDetailBinding): RecyclerView.ViewHolder(chatDetailBinding.root){
            init {
                chatDetailBinding.root.setOnClickListener {
                    val intent = Intent(activity, ChatTranslationActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(chatDetailBinding.root.context, "클릭된 아이템 = ${chatDetailBinding.chatDetailName.text}", Toast.LENGTH_LONG).show()
                }
            }

            fun setChat(item : ChatDTO){
                chatDetailBinding.chatDetailName.text = item.name
                chatDetailBinding.chatDetailContent.text = item.content
            }
        }
    }
}

