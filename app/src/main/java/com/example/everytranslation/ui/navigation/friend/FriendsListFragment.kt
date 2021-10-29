package com.example.everytranslation.ui.navigation.friend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.R
import com.example.everytranslation.adapter.FriendListViewAdapter
import com.example.everytranslation.data.FriendDTO
import com.example.everytranslation.data.service.FriendApiService
import com.example.everytranslation.databinding.FragmentFriendsListBinding
import com.example.everytranslation.databinding.FriendDetailBinding
import com.example.everytranslation.db.dto.Friend

class FriendsListFragment : Fragment() {

    private var friendList = ArrayList<Friend>()

    companion object{
        fun newInstance() : FriendsListFragment {
            return FriendsListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_friends_list, container, false)
        val viewAdapter = FriendListViewAdapter(requireContext(),friendList)
        val btn_add_friend = view.findViewById<Button>(R.id.friendlist_add_friends)
        val friendsListFragmentRecyclerView = view.findViewById<RecyclerView>(R.id.friendslistfragment_recyclerview)

        friendsListFragmentRecyclerView.adapter=viewAdapter
        friendsListFragmentRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        friendsListFragmentRecyclerView.setHasFixedSize(true)

        FriendApiService.instance.getFriendAll(){
            for(friend in it){
                viewAdapter.setFriend(friend)
            }
        }

        btn_add_friend.setOnClickListener{
            val intent = Intent(activity,AddFriendActivity::class.java )
            startActivity(intent)
        }

        return view
    }



}
