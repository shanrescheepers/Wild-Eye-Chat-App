package com.example.wildeyechatapp.services

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

//AuthRepository
//For all Firebase Auth Functionality
class AuthService {
    val currentUser: FirebaseUser? = Firebase.auth.currentUser;

    fun hasUser(): Boolean = Firebase.auth.currentUser != null;

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty();

//    Register functionality
  suspend  fun registerNewUser(
    email: String,
    standNumber: String,
    name: String,
    password: String,

    createdUserSuccessfully:(String)-> Unit

    )= withContext(Dispatchers.IO){
        Firebase.auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(it.isSuccessful){ //if successfully created
                    Log.d("Registered a new user successfully!", it.result.user?.uid.toString());
                    it.result.user?.uid?.let {
                            it1 -> createdUserSuccessfully.invoke(it1) }
                } else {
                    Log.d("Error occurred when trying to register a user", it.exception?.localizedMessage.toString())
                    createdUserSuccessfully.invoke("");
                }
            }.await()
    }

    //    Login functionality
    suspend  fun loginUser(
        email: String,
        password: String,
        isCompleted:(Boolean)-> Unit

    )= withContext(Dispatchers.IO){
        Firebase.auth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (it.isSuccessful) { //if successfully created
                    Log.d("Login: ", "successful")
                    isCompleted.invoke(true)
                } else {
                    Log.d("Login: error", it.exception?.localizedMessage.toString())
                    isCompleted.invoke(false)
                }
            }.await()
    }

    //  Sign Out User
    fun signOutUser(){
        Firebase.auth.signOut();
    }
}