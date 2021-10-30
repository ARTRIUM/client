package com.example.everytranslation.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ChatViewModelFactory() : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel() as T

    }
}