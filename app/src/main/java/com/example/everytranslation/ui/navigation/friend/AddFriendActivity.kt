package com.example.everytranslation.ui.navigation.friend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.everytranslation.databinding.ActivityAddFriendBinding
import com.example.hanium.SignUpActivity
import android.R
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.everytranslation.MainActivity
import com.example.everytranslation.data.model.AddFriendDTO
import com.example.everytranslation.data.service.FriendApiService
import com.example.everytranslation.db.dto.User
import com.example.everytranslation.utils.MyApplication


class AddFriendActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddFriendBinding
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra("user")!!

        binding.addFriendBtn.setOnClickListener{
            val friendText = binding.addFriendText.text.toString()

            FriendApiService.instance.addFriend(AddFriendDTO(friendText)) {

                if (friendText.contains('@') && it.success) {
                    Toast.makeText(this, "친구 추가에 성공하였습니다.", Toast.LENGTH_LONG).show()
                    //Log.d("주석", it.friendName)
                    finish()
                }
                else{
                    Toast.makeText(this,"친구 추가에 실패하셨습니다.",Toast.LENGTH_LONG).show()
                }
            }



        }
        binding.addFriendExit.setOnClickListener{
            finish()
        }

        binding.addFriendMyEmail.text = user.email
    }

}