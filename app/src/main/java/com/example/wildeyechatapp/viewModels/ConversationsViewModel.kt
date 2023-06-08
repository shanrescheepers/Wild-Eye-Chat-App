package com.example.wildeyechatapp.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wildeyechatapp.models.ConversationPeople
import com.example.wildeyechatapp.services.FireStoreService
import kotlinx.coroutines.launch

class ConversationsViewModel(
    private val service: FireStoreService = FireStoreService()

):ViewModel() {
    private val _convoLists = mutableStateListOf<ConversationPeople>()
    val convoList: List<ConversationPeople> = _convoLists
    init{
        getConversations()
    }

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