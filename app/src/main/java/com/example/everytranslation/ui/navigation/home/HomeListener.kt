package com.example.everytranslation.ui.navigation.home

interface HomeListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String,type: Int)
}