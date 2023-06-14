package com.example.wildeyechatapp.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wildeyechatapp.models.ConversationPeople
import com.example.wildeyechatapp.models.User
import com.example.wildeyechatapp.services.AuthService
import com.example.wildeyechatapp.services.FireStoreService
import kotlinx.coroutines.launch

class ConversationsViewModel(
    private val service: FireStoreService = FireStoreService()


):ViewModel() {

    var currentUser : User? = null
    var currentUserId = ""
    var currentUserImage = "https://firebasestorage.googleapis.com/v0/b/wild-eye-chat-app.appspot.com/o/default_profile.png?alt=media&token=5283c9b7-e4a9-4423-b8c0-59ba08f9e61f"

    init{
        getCurrentProfile()

        getConversations()
    }
    fun getProfileImage(): String {
        return currentUserImage
    }

    fun getCurrentProfile() = viewModelScope.launch {
        currentUserId = AuthService().getUserId()
        if(currentUserId.isNotBlank()){
            service.getUserProfile(currentUserId){
                currentUser = it
                if (it != null) {
                    currentUserImage = it.profileImageUrl
                }
                Log.d("Received user info", it.toString())
            }
        }
    }

    private val _convoLists = mutableStateListOf<ConversationPeople>()
    val convoList: List<ConversationPeople> = _convoLists


           private fun getConversations() = viewModelScope.launch{
                service.getAllConversations (){ data ->
                    if(data != null){
                        for(document in data){
                     _convoLists.add(document)

                }

            }
        }
    }
}