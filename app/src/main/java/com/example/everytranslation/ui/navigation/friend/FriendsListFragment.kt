package com.example.everytranslation.ui.navigation.friend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everytranslation.R
import com.example.everytranslation.data.FriendDTO
import com.example.everytranslation.databinding.FragmentFriendsListBinding
import com.example.everytranslation.databinding.FriendDetailBinding

class FriendsListFragment : Fragment() {

    companion object{
        fun newInstance() : FriendsListFragment {
            return FriendsListFragment()
        }
    }

    lateinit var fragmentbinding: FragmentFriendsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //친구 리스트
        fragmentbinding = FragmentFriendsListBinding.inflate(inflater, container, false)
        fragmentbinding.friendslistfragmentRecyclerview.adapter = FriendslistRecyclerViewAdapter()
        fragmentbinding.friendslistfragmentRecyclerview.layoutManager = LinearLayoutManager(activity)

        fragmentbinding.friendlistAddFriends.setOnClickListener{
            val intent = Intent(activity,AddFriendActivity::class.java )
            startActivity(intent)
        }

        return fragmentbinding.root
    }

    inner class FriendslistRecyclerViewAdapter : RecyclerView.Adapter<FriendslistRecyclerViewAdapter.Holder>(){

        var friendDTOs : ArrayList<FriendDTO> = arrayListOf()
        //var friendDTOs : ArrayList<String> = arrayListOf()
        init{
            // reset
            friendDTOs.clear()

            // friends data list loading
            friendDTOs.add(FriendDTO("people1","url1"))
            friendDTOs.add(FriendDTO("people2","url2"))
            friendDTOs.add(FriendDTO("people3","url3"))
            friendDTOs.add(FriendDTO("people4","url4"))
            friendDTOs.add(FriendDTO("people5","url5"))


            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val friendDetailBinding = FriendDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return Holder(friendDetailBinding)
        }

        override fun getItemCount(): Int {
            return friendDTOs.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            var item = friendDTOs.get(position)
            holder.setChat(item)
        }

        inner class Holder(val friendDetailBinding: FriendDetailBinding): RecyclerView.ViewHolder(friendDetailBinding.root){
            init {
                friendDetailBinding.root.setOnClickListener {
                    Toast.makeText(friendDetailBinding.root.context, "클릭된 아이템 = ${friendDetailBinding.friendName.text}", Toast.LENGTH_LONG).show()
                }
            }

            fun setChat(item : FriendDTO){
                friendDetailBinding.friendName.text = item.name
               // friendDetailBinding.friendImageUrl.text = item.imageUrl
            }
        }
    }

}
