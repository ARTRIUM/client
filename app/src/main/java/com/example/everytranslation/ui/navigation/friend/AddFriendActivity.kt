package com.example.everytranslation.ui.navigation.friend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.everytranslation.databinding.ActivityAddFriendBinding
import com.example.hanium.SignUpActivity

class AddFriendActivity : AppCompatActivity() {
    val binding by lazy { ActivityAddFriendBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addFriendBtn.setOnClickListener{
            var Friendtext = binding.addFriendText.getText().toString()
            if(Friendtext.contains('@')==true) {
                Toast.makeText(this, Friendtext+"님을 추가하셨습니다.", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"친구 추가에 실패하셨습니다.",Toast.LENGTH_LONG).show()
            }
        }
        binding.addFriendExit.setOnClickListener{
            val intent = Intent(this,FriendsListFragment::class.java)
            startActivity(intent)
        }
    }
}