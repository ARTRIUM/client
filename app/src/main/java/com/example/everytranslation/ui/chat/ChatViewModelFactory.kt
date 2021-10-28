package com.example.everytranslation.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.everytranslation.data.repository.ChatRepository

class ChatViewModelFactory(private val repository: ChatRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(repository) as T

    }
}