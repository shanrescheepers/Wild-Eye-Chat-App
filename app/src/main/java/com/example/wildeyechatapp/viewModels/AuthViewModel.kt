package com.example.wildeyechatapp.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import android.widget.Toast
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wildeyechatapp.services.AuthService
import com.example.wildeyechatapp.services.FireStoreService
import kotlinx.coroutines.launch
import java.lang.Exception

//   single auth model for all authentication
class AuthViewModel(

    private val authService: AuthService = AuthService(),
//    private val fireStoreService: FireStoreService = FireStoreService()

    ): ViewModel() {
    val currentUser = authService.currentUser
    val hasUser: Boolean get() = authService.hasUser()

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
//        authUiState.
    }

//    execute register func
    fun createNewUser(context: Context) = viewModelScope.launch {
        authUiState = authUiState.copy(errorMessage = "")
        try {
            if(authUiState.registerUsername.isBlank() || authUiState.registerEmail.isBlank()
                || authUiState.registerStandNumber.isBlank() || authUiState.registerPassword.isBlank()){
                authUiState = authUiState.copy(errorMessage = "Please fill in all the fields")
            } else {
                authUiState = authUiState.copy(isLoading = true)
                authService.registerNewUser(

                    authUiState.registerEmail,
                    authUiState.registerStandNumber,
                    authUiState.registerUsername,
                    authUiState.registerPassword,
                    "https://firebasestorage.googleapis.com/v0/b/wild-eye-chat-app.appspot.com/o/default_profile.png?alt=media&token=5283c9b7-e4a9-4423-b8c0-59ba08f9e61f"
                ){userId->
                    if (userId.isNotBlank()){
        FireStoreService().createUserInDatabase(uid =userId, email = authUiState.registerEmail,
            standNumber = authUiState.registerStandNumber ,
            username = authUiState.registerUsername, profileImageUrl = "profileImageUrl", token = "token" ){
            if (it){
                Log.d("Register success", userId)
                Toast.makeText(context, "Registration Complete!",
                Toast.LENGTH_SHORT).show()
            authUiState =  authUiState.copy(authSuccess = true)
            } else{
                Log.d("Register failed", userId)
                Toast.makeText(context, "Registration Failed!",
                    Toast.LENGTH_SHORT).show()
                authUiState =  authUiState.copy(authSuccess = false)
            }
        }


                    } else {
                        Log.d("Error Registering", "something went wrong")
                        Toast.makeText(context, "Registration Failed!",
                            Toast.LENGTH_SHORT).show()
                        authUiState =  authUiState.copy(authSuccess = false)
                        authUiState = authUiState.copy(errorMessage = "Invalid Email/Password")
                    }
                }
            }
        }catch (e: Exception){
        Log.d("Error Registering: ", e.localizedMessage)
            e.printStackTrace()
        }finally {
        authUiState = authUiState.copy(isLoading = false)
        }
    }

    // execute login func
    fun loginUser(context: Context) = viewModelScope.launch {
        authUiState = authUiState.copy(errorMessage = "")
        try {
            //for validation
            if(authUiState.loginEmail.isBlank() || authUiState.loginPassword.isBlank()){
                authUiState = authUiState.copy(errorMessage = "Please fill in all the fields")
            } else {
                authUiState = authUiState.copy(isLoading = true)
                // Als wat WECA nodig het
                authService.loginUser(
                    authUiState.loginEmail,
                    authUiState.loginPassword,
                ){isCompleted->
                    if (isCompleted){
                        Log.d("Login success","Welcome!")
                        Toast.makeText(context, "Login Complete!",
                            Toast.LENGTH_SHORT).show()
                        authUiState =  authUiState.copy(authSuccess = true)
                    } else {
                        Log.d("Error Loggin in", "something went wrong")
                        Toast.makeText(context, "Login Failed!",
                            Toast.LENGTH_SHORT).show()
                        authUiState =  authUiState.copy(authSuccess = false)
                        authUiState = authUiState.copy(errorMessage = "Invalid Email/Password")
                    }
                }
            }
        }catch (e: Exception){
            Log.d("Error Registering: ", e.localizedMessage)
            e.printStackTrace()
        }finally {
            authUiState = authUiState.copy(isLoading = false)
        }
    }
}

// THESE VALUES ARE FOR MY FRONTEND STATE CHANGE MANAGEMENT
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