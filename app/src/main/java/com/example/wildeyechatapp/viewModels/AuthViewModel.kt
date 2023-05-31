package com.example.wildeyechatapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wildeyechatapp.services.AuthService

//   single auth model for all authentication
class AuthViewModel(
    authService: AuthService = AuthService()
    ): ViewModel() {
    val currentUser = authService.currentUser
    val hasUser: Boolean = authService.hasUser()

    // Instance of our state values
    var authUiState by mutableStateOf(AuthUiState())
        private set

    // function to handle the value updates of my states
    fun handleInputStateChanges(target: String, value: String){
        if(target == "loginEmail"){
            authUiState = authUiState.copy(loginEmail = value)
        } else if (target == "loginPassword"){
            authUiState = authUiState.copy(loginPassword = value)
        } else if (target == "registerUsername"){
            authUiState = authUiState.copy(registerUsername = value)
        } else if (target == "registerEmail"){
            authUiState = authUiState.copy(registerEmail = value)
        } else if (target == "registerPassword"){
            authUiState = authUiState.copy(registerPassword = value)
        } else if (target == "registerStandNumber"){
            authUiState = authUiState.copy(registerStandNumber = value)
        }
        authUiState.
    }
}

// THESE VALUES ARE FOR MY FRONEND STATE CHANGE MANAGEMENT
data class AuthUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val authSuccess: Boolean = false,

//    State Values for my login
    val loginEmail: String = "",
    val loginPassword: String = "",

//    State Values for registering
    val registerEmail: String = "",
    val registerPassword: String = "",
    val registerUsername: String = "",
    val registerStandNumber: String = "",
)