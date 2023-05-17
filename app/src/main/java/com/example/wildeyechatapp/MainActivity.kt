package com.example.wildeyechatapp

import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.wildeyechatapp.screens.LoginScreen
import com.example.wildeyechatapp.screens.RegisterScreen
import com.example.wildeyechatapp.ui.theme.BGcolor
import com.example.wildeyechatapp.ui.theme.WildEyeChatAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WildEyeChatAppTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier.fillMaxSize().background(color = BGcolor),

                ) {
//                    Greeting("Android")
//                    RegisterScreen()
//                    LoginScreen()
                    Navigation()
                }
            }
        }
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
       Navigation()
    }
}