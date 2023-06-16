package com.example.wildeyechatapp.services

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
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
    profileImageUrl: String,
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

    fun changeUserEmail(currentEmail: String, currentPassword: String, newEmail: String, onComplete: (Boolean) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        val credential = EmailAuthProvider.getCredential(currentEmail, currentPassword)

        user?.reauthenticate(credential)
            ?.addOnCompleteListener { reAuthTask ->
                if (reAuthTask.isSuccessful) {
                    Log.d(TAG, "User re-authenticated.")

                    val updatedUser = FirebaseAuth.getInstance().currentUser
                    updatedUser?.updateEmail(newEmail)
                        ?.addOnCompleteListener { updateEmailTask ->
                            if (updateEmailTask.isSuccessful) {
                                Log.d(TAG, "User email address updated.")
                                onComplete(true)
                            } else {
                                Log.e(TAG, "Failed to update user email address.", updateEmailTask.exception)
                                onComplete(false)
                            }
                        }
                } else {
                    Log.e(TAG, "User re-authentication failed.", reAuthTask.exception)
                    onComplete(false)
                }
            }
    }

}