package com.example.wildeyechatapp.services

import android.provider.Telephony.Sms.Conversations
import android.util.Log
import com.example.wildeyechatapp.models.ConversationPeople
import com.example.wildeyechatapp.models.User
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

const val CONVERSATION_REFERENCE ="conversations"
const val USER_REFERENCE ="users"


class FireStoreService {
    val db = Firebase.firestore
    private val  conversationsRef: CollectionReference = db.collection(CONVERSATION_REFERENCE)
    private val  userRef: CollectionReference = db.collection(USER_REFERENCE)


    suspend fun getAllConversations(
        onSuccess: (List<ConversationPeople>?) -> Unit
    ){
        conversationsRef.orderBy("title").get()
            .addOnSuccessListener {
                Log.d("Conversation Data", it.toString())
                onSuccess(it.toObjects(ConversationPeople::class.java))
            }
            .addOnFailureListener {
                Log.d("Error trying to retrieve data:", it.localizedMessage)
                onSuccess(null)
            }
            .await()

    }

   fun createUserInDatabase(
        uid: String,
        email: String,
        standNumber: String,
        username: String,
        profileImageUrl: String,
        token: String?,
        onSuccess: (Boolean) -> Unit
    ){
    db.collection("users").document(uid)
        .set(User(id = uid,
            username=username,
            email=email,
            standNumber=standNumber,
            profileImageUrl="",
            token="")
        )
        .addOnSuccessListener {
            Log.d("USER: Create User In DB Successful", "YAY!")
            onSuccess.invoke(true)
        }
        .addOnFailureListener{
            Log.d("USER: Create User In DB Failed", it.localizedMessage)
            onSuccess(false)
        }
    }
}