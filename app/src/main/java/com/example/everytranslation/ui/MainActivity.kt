package com.example.everytranslation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.everytranslation.databinding.ActivityMainBinding
import com.example.everytranslation.db.AppDatabase
import com.example.everytranslation.db.dto.User
import com.example.everytranslation.ui.activity.MypageActivity
import com.example.everytranslation.ui.navigation.Chatting.ChattingListFragment
import com.example.everytranslation.ui.navigation.friend.FriendsListFragment
import com.example.everytranslation.ui.navigation.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var user : User
    private lateinit var binding: ActivityMainBinding
    private lateinit var chatListFragment: ChattingListFragment
    private lateinit var friendsListFragment: FriendsListFragment
    private lateinit var homeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = intent.getParcelableExtra("user")!!
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        Thread {
            user = AppDatabase.getInstance(applicationContext).userDao().get(user.userId)
        }
        //bottom navigation
        binding.bottomNavigation.apply {
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.action_people_list -> {
                        friendsListFragment = FriendsListFragment.newInstance()
                        supportFragmentManager.beginTransaction().replace(R.id.main_content,friendsListFragment).commit()
                        return@setOnItemSelectedListener true
                    }

                    R.id.action_chatting -> {
                        chatListFragment = ChattingListFragment.newInstance(user)
                        supportFragmentManager.beginTransaction().replace(R.id.main_content,chatListFragment).commit()
                        return@setOnItemSelectedListener true
                    }

                    R.id.action_home -> {
                        homeFragment = HomeFragment.newInstance(user)
                        supportFragmentManager.beginTransaction().replace(R.id.main_content,homeFragment).commit()
                        return@setOnItemSelectedListener true
                    }
                }
                false
            }
        }

        binding.bottomNavigation.itemIconTintList = null

        // Set default screen
        binding.bottomNavigation.selectedItemId = R.id.action_home

        //test
    }

}
