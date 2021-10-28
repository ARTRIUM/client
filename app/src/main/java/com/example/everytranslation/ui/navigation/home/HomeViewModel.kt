package com.example.everytranslation.ui.navigation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.everytranslation.data.model.ApiResult
import com.example.everytranslation.data.repository.HomeRepository
import com.example.everytranslation.utils.MyApplication
import com.example.everytranslation.utils.NetworkStatus
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    // homeListener
    var homeListener: HomeListener? = null
    var networkErrorString = "네트워크 연결을 확인해주세요."

    // 미팅시작
    private val _startMeet : MutableLiveData<ApiResult<String>> = MutableLiveData()
    val startMeet : LiveData<ApiResult<String>> = _startMeet

    fun startMeet() = viewModelScope.launch {
        if (NetworkStatus.status) {
            Log.d("startMeet","미팅시작")
            _startMeet.value = homeRepository.startMeet(MyApplication.prefs.getUserId())
        } else {
            homeListener?.onFailure(networkErrorString, 99)
        }
    }

}
