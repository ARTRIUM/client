package com.example.everytranslation.ui.navigation.Chatting

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.R
import com.example.everytranslation.adapter.ChattingListAdapter
import com.example.everytranslation.data.ChatDTO
import com.example.everytranslation.databinding.ChatDetailBinding
import com.example.everytranslation.databinding.FragmentChattingListBinding
import com.example.everytranslation.db.AppDatabase
import com.example.everytranslation.db.dto.ChatRoom
import com.example.everytranslation.db.dto.User
import com.example.everytranslation.ui.chat.ChatActivity
import com.example.everytranslation.ui.chat.ChatTranslationActivity
import com.example.everytranslation.ui.navigation.friend.AddFriendActivity
import java.util.logging.Level.INFO

class ChattingListFragment(val user : User) : Fragment() {

    companion object{
        fun newInstance(user: User) : ChattingListFragment {
            Log.d("user:{}",user.name.toString());
            return ChattingListFragment(user)
        }
    }

    lateinit var adapter : ChattingListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view:View = inflater.inflate(R.layout.fragment_chatting_list, container, false)
        val btn_addRoom : ImageView = view.findViewById(R.id.chatlist_add_friends);

        btn_addRoom.setOnClickListener(){
            val intent = Intent(activity, ChatActivity::class.java )
            startActivity(intent)
        }

        return view;
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChattingListAdapter(requireContext(), user, requireActivity())

        val db = AppDatabase.getInstance(requireContext())
        db.roomDao().getAll().observe(viewLifecycleOwner){
            adapter.setRooms(it)
            observerNewMessage(it, db)
        }
    }

    private fun observerNewMessage(rooms : List<ChatRoom>, db : AppDatabase){
        for(room in rooms) {
            db.messageDao().getAll(room.roomId).observe(viewLifecycleOwner) { messages ->
                if(messages.isNotEmpty())
                    adapter.notifyItemChangedBy(room.roomId, messages.last())
            }
        }
    }
}

