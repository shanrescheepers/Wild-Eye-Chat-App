package com.example.wildeyechatapp.services

import android.content.ContentValues
import android.provider.Telephony.Sms.Conversations
import android.util.Log
import androidx.core.content.contentValuesOf
import com.example.wildeyechatapp.models.ConversationPeople
import com.example.wildeyechatapp.models.Message
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
        Log.d("Getting Convos in Firestore", "")
        val conversations = arrayListOf<ConversationPeople>()

        conversationsRef.orderBy("title").get()
            .addOnSuccessListener {
//                Log.d("Conversation Data", it.toString())
                for(document in it){
                    conversations.add(
                        ConversationPeople(
                            id = document.id,
                            title = document.data["title"].toString(),
                            image = document.data["image"].toString()
                        )
                    )
                }
                Log.d("Conversation Data Pulled", conversations.toString())
                onSuccess(conversations)
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
            onSuccess.invoke(false)
        }
    }
    suspend fun addNewMessage(
        newMessage : Message,
        chatId: String,
        onSuccess: (Boolean) -> Unit
    ){
        conversationsRef.document(chatId).collection("messages")
            .add(newMessage)
            .addOnSuccessListener {  Log.d("New Message Added", it.id)
            onSuccess.invoke(true)}
            .addOnFailureListener{
                Log.d("Failure to add New Message ", it.localizedMessage)
                it.printStackTrace()
                onSuccess.invoke(false)
            }.await()
    }
//Get Live CHats
//     val realTimeMessageRef = conversationsRef.document()

    suspend fun getUserProfile(
        uid: String,
        onSuccess: (User?) -> Unit
    ){
        Log.d("Getting UID ", uid)
        userRef.document(uid).get().addOnSuccessListener {
            if(it != null){
                Log.d(ContentValues.TAG, "Document Snapshot whatver")
                onSuccess.invoke(it?.toObject(User::class.java))
            }else {
                Log.d(ContentValues.TAG, "No such Document Snapshot whatver")
                onSuccess.invoke(null)
            }
        }
            .addOnFailureListener{
                exception ->
                Log.d(ContentValues.TAG, "Get failed with", exception)
                onSuccess.invoke(null)
            }.await()
    }
}