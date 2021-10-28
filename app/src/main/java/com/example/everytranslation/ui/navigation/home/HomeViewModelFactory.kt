package com.example.everytranslation.ui.navigation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.everytranslation.data.repository.HomeRepository

class HomeViewModelFactory (private val repository: HomeRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T

    }
}