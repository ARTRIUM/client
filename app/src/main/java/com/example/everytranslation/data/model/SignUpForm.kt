package com.example.everytranslation.data.model

data class SignUpForm(
    val name : String,
    val password : String,
    val email : String,
    val language : String,
    val permissionCode : String
)