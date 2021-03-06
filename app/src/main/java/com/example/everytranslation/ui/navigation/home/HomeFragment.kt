package com.example.everytranslation.ui.navigation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.everytranslation.MainActivity
import com.example.everytranslation.R
import com.example.everytranslation.databinding.FragmentHomeBinding
import com.example.everytranslation.db.dto.User
import com.example.everytranslation.ui.activity.MypageActivity
import com.example.everytranslation.ui.chat.ChatActivity
import com.example.everytranslation.ui.chat.ChatDrawerActivity
import com.example.everytranslation.utils.MyApplication
import com.example.everytranslation.utils.NetworkConnection
import com.example.everytranslation.utils.NetworkStatus
import com.example.everytranslation.utils.toast

class HomeFragment(val user : User) : Fragment(), HomeListener {

    companion object{

        fun newInstance(user : User) : HomeFragment {
            Log.d("user:{}",user.name.toString());
            return HomeFragment(user)
        }
    }

    lateinit var fragmentbinding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    lateinit var viewModelFactory: HomeViewModelFactory
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ? {
        // Inflate the layout for this fragment
        fragmentbinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)


        val connection = NetworkConnection(MyApplication.instance.context())
        connection.observe(viewLifecycleOwner) { isConnected ->
            NetworkStatus.status = isConnected
        }

        initViewModel()

        if (user.language == "KOREAN")
            fragmentbinding.meetText.text = "안녕하세요, " + user.name + "님!"
        else if (user.language == "ENGLISH")
            fragmentbinding.meetText.text = "Hello, " + user.name + "!"

        fragmentbinding.meetReservation.setOnClickListener {
            val intent = Intent(getActivity(), MeetingReservationActivity::class.java)
            startActivity(intent)
        }

        fragmentbinding.meetStart.setOnClickListener{
            var intent = Intent(getActivity(),ChatDrawerActivity::class.java)
            intent.putExtra("user",user)
            startActivity(intent)
        }

        //tool bar
        fragmentbinding.imageView.setOnClickListener {
            val intent = Intent(activity, MypageActivity::class.java)
            startActivity(intent)
        }


        return fragmentbinding.root
    }

    private fun initViewModel(){
        viewModelFactory = HomeViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.homeListener = this
        fragmentbinding.viewModel = viewModel
        fragmentbinding.lifecycleOwner = this

        viewModel.startMeet.observe(viewLifecycleOwner) {
            if(it.success) {
                val intent = Intent(activity, ChatActivity::class.java)
                startActivity(intent)
            }
            else {
                Log.d("homeFragment", "미팅시작 실패")
            }
        }
    }

    override fun onStarted() {}

    override fun onSuccess() {}

    override fun onFailure(message: String, type: Int) {}

}