package com.example.wildeyechatapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.ui.platform.setContent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wildeyechatapp.screens.ConversationScreen
import com.example.wildeyechatapp.screens.LoginScreen
import com.example.wildeyechatapp.screens.RegisterScreen
import com.example.wildeyechatapp.services.BackgroundService
import com.example.wildeyechatapp.ui.theme.BGcolor
import com.example.wildeyechatapp.ui.theme.InputBorderColor
import com.example.wildeyechatapp.ui.theme.TitleColor
import com.example.wildeyechatapp.ui.theme.WildEyeChatAppTheme
import com.example.wildeyechatapp.viewModels.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
0
            val authViewModel: AuthViewModel = viewModel(modelClass = AuthViewModel::class.java)
            WildEyeChatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    Navigation(authViewModel = authViewModel)
//                    Greeting("Android")
//                    RegisterScreen()
//                    LoginScreen()
//                      ConversationScreen()

                }
            }

        }
        //Launch background service
        val  serviceIntent= Intent(
            this,
            BackgroundService::class.java
        )
        startService(serviceIntent)
    }
}

@Composable

fun Greeting(name: String, modifier: Modifier = Modifier) {

    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WildEyeChatAppTheme {
//        RegisterScreen()
//       Navigation()
//        LoginScreen()
//        ConversationScreen()

    }
}