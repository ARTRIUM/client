package com.example.everytranslation.ui.navigation.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.everytranslation.MainActivity
import com.example.everytranslation.R
import com.example.everytranslation.databinding.ActivityMeetingReservationBinding
import com.example.everytranslation.ui.chat.ChatDrawerActivity

class MeetingReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeetingReservationBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_meeting_reservation)

            binding.reservationBtn.setOnClickListener{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }


        }
}