package com.example.everytranslation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.everytranslation.data.repository.AuthRepository

class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T

    }
}