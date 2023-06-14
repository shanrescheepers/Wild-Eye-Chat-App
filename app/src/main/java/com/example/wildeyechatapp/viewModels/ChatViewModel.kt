package com.example.wildeyechatapp.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wildeyechatapp.models.ConversationPeople
import com.example.wildeyechatapp.models.Message
import com.example.wildeyechatapp.models.User
import com.example.wildeyechatapp.services.AuthService
import com.example.wildeyechatapp.services.FireStoreService
import kotlinx.coroutines.launch

class ChatViewModel(
    private val service: FireStoreService = FireStoreService()

):ViewModel() {
    private val _messageLists = mutableStateListOf<Message>()

    val messageList: List<Message> = _messageLists
//   private var _newMessage = mutableStateOf("")
//
//    fun updateNewMessage(value: String){
//        _newMessage = value

//        val from: String = "",
//    val fromUserId: String = "",
//    val fromUserProfilePic: String = "",
//    val message: String = "",
//    val timestamp: Timestamp = Timestamp.now(),
//    }

    private var currentUser : User? = null
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

    fun sendNewMessage(body: String, chatId: String)=viewModelScope.launch {
        if(body.isNotBlank() && chatId.isNotBlank()){

            var sentMessage = Message(
                message = body,
                from = currentUser?.username ?: "",
                fromUserId = currentUser?.id?:"",
                fromUserProfilePic = currentUser?.profileImageUrl?:"",
            )

            service.addNewMessage(
                newMessage= sentMessage,
                chatId = chatId
            ){
                if(it){
                    Log.d("Added new message successful", it.toString())
                }
            }
        }
    }
}

