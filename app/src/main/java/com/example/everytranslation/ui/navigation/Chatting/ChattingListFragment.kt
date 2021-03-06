package com.example.everytranslation.ui.navigation.Chatting

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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


class ChattingListFragment(val user : User) : Fragment() {

    companion object{
        fun newInstance(user: User) : ChattingListFragment {
            Log.d("user:{}",user.name.toString());
            return ChattingListFragment(user)
        }
    }

    lateinit var viewAdapter : ChattingListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view:View = inflater.inflate(R.layout.fragment_chatting_list, container, false)
        val btn_addRoom : Button = view.findViewById(R.id.chatlist_add_friends);
        val chattingListFragment = view.findViewById<RecyclerView>(R.id.chattinglistfragment_recyclerview)

        viewAdapter = ChattingListAdapter(requireContext(), user, requireActivity())

        chattingListFragment.adapter=viewAdapter
        chattingListFragment.layoutManager=LinearLayoutManager(context)
        chattingListFragment.setHasFixedSize(true)

        btn_addRoom.setOnClickListener(){
            val intent = Intent(activity, ChatActivity::class.java )
            startActivity(intent)
        }

        return view;
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val db = AppDatabase.getInstance(requireContext())
        db.roomDao().getAll().observe(viewLifecycleOwner){
            viewAdapter.setRooms(it)
            observerNewMessage(it, db)
        }
    }

    private fun observerNewMessage(rooms : List<ChatRoom>, db : AppDatabase){
        for(room in rooms) {
            db.messageDao().getAll(room.roomId).observe(viewLifecycleOwner) { messages ->
                if(messages.isNotEmpty())
                    viewAdapter.notifyItemChangedBy(room.roomId, messages.last())
            }
        }
    }
}

