package com.example.wildeyechatapp.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wildeyechatapp.models.User
import com.example.wildeyechatapp.services.AuthService
import com.example.wildeyechatapp.services.FireStoreService
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val service: FireStoreService = FireStoreService()

):ViewModel() {



    var currentUser : User? = null
    var currentUserId = ""

    init {
        getCurrentProfile()
    }
    private fun getCurrentProfile() = viewModelScope.launch {
        currentUserId = AuthService().getUserId()

        if(currentUserId.isNotBlank()){
            service.getUserProfile(currentUserId){
                currentUser = it
                Log.d("Received user info", it.toString())
            }
        }
    }

    fun updateUser(
        uid: String,
        username: String,
        image: String,
//        email: String,
        stand: String,

        onSuccess: (Boolean) -> Unit
    ) {
        // Call the function from the previous code snippet
        // add email
        service.updateUserInDatabase(uid, username, image, stand, onSuccess)
    }

}

