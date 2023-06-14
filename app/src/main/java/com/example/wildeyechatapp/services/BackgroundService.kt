package com.example.wildeyechatapp.services

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.wildeyechatapp.models.Message
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BackgroundService: Service() {
    var onMessageListener: ListenerRegistration? = null

    var authService = AuthService()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread{
            while (true){
            try{
                Log.d("Service", "Running")

                if (authService.hasUser()){
                    if(onMessageListener == null){
                        startFireStoreListener()
                    }else{
                        Log.e("Service", "already listening to data")

                    }

                } else{
                    Log.d("Service", "user not logged in")

                }
                Thread.sleep(15000)
            }catch (e:Exception){
                onMessageListener = null
                e.printStackTrace()
            }
        }
        }.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        onMessageListener = null
        Log.d("Service", "destroy")

        super.onDestroy()
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun startFireStoreListener() {

        Log.d("start listening", "yes")

        val collectionRef = Firebase.firestore
            .collectionGroup("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(10)

        onMessageListener = collectionRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.d("Listerner went wrong...", e.localizedMessage)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                Log.d("REceived realtime...", snapshot.toString())
                for (dc in snapshot.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED ->
                            if(authService.currentUser?.uid != dc.document.data["fromUserId"].toString())
                                MyNotification(
                                this,
                                "New Message",
                                dc.document.data["message"].toString()
                            ).showNotification()
                        DocumentChange.Type.MODIFIED ->
                            Log.d("service notification data modified", "Modified message: ${dc.document.data}" )
                        DocumentChange.Type.REMOVED ->
                            Log.d("service notification data removed", "Removed message: ${dc.document.data}" )
                    }


                }


        }
    }
    }
}