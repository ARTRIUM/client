package com.example.everytranslation.ui.navigation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class HomeViewModelFactory () : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel() as T

    }
}