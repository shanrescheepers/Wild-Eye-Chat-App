package com.example.wildeyechatapp.models

// uid: String,
//        username: String,
//        email: String,
//        standNumber: String,
//        profileImageUrl: String,
//        token: String?,
//        onSuccess: (Boolean) -> Unit
data class User(
    val id: String = "",
    val email:String = "",
    val standNumber:String = "",
    val username: String = "",


    val profileImageUrl:String = "",
    val token:String = "",
)
