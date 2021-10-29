package com.example.everytranslation.ui.navigation.friend

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.everytranslation.data.model.ApiResult
import com.example.everytranslation.ui.auth.AuthListener
import com.example.everytranslation.utils.NetworkStatus
import kotlinx.coroutines.launch

class FriendListViewModel() : ViewModel() {

    // Add friend
    var addFriendEmail = ObservableField<String>()

    //Add friend listener
    var addFriendListener : AuthListener?=null

    // 친구 추가
    private val _addFriendResponse : MutableLiveData<ApiResult<String>> = MutableLiveData()
    val addFriendResponse: LiveData<ApiResult<String>> get() = _addFriendResponse

    private val _addFriendLoading = MutableLiveData<Boolean>()
    val addFriendLoading : LiveData<Boolean> get() = _addFriendLoading

    fun addFriend() = viewModelScope.launch {
        if(NetworkStatus.status){
           _addFriendLoading.postValue(true)
//           _addFriendResponse.value = friendRepository.addFriend(AddFriendForm())

        }


    }




    //친구 추가 필드 확인
    fun checkAddFriendField() {
        if(addFriendEmail.get().isNullOrEmpty()){
            addFriendListener?.onFailure("이메일을 입력해 주세요",1)
            return
        }
        addFriend()
    }

}