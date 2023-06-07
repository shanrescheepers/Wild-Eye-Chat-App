@file:Suppress("DEPRECATION")

package com.example.wildeyechatapp.screens

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wildeyechatapp.R
import com.example.wildeyechatapp.ui.theme.BlackButton
import com.example.wildeyechatapp.ui.theme.ButtonTextColor
import com.example.wildeyechatapp.ui.theme.InputBorderColor
import com.example.wildeyechatapp.ui.theme.InputFieldColor
import com.example.wildeyechatapp.ui.theme.LightGrey
import com.example.wildeyechatapp.ui.theme.WildEyeChatAppTheme
import com.example.wildeyechatapp.ui.theme.backgroundColor
import com.example.wildeyechatapp.ui.theme.warningColor
import com.example.wildeyechatapp.viewModels.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun LoginScreen(
    context: Context = LocalContext.current,
    view: View = LocalView.current,

    authViewModel: AuthViewModel? = null,
    navToRegister:() -> Unit,
    modifier: Modifier = Modifier){


//    get vals from view model

    val authUiState =  authViewModel?.authUiState
    val error = authUiState?.errorMessage != null
    val context = LocalContext.current


//state variables
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
    val titleColor = R.color.titleColor

//    Text(text="Login Screen working")
    Column(
        modifier = Modifier
            .background(InputFieldColor)
            .fillMaxSize(),


        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .width(width = 260.dp)
                .align(alignment = Alignment.CenterHorizontally),

            )


//        Spacer(modifier = Modifier.size(10.dp))


        Text(
            text = "LOGIN BY ADDING YOUR DETAILS BELOW",
            style = MaterialTheme.typography.displayMedium.copy(textAlign = TextAlign.Center),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = BlackButton,
            modifier = Modifier.padding(vertical = 15.dp)
        )

        Spacer(modifier = Modifier.size(7.dp))
//        Card(colors = CardDefaults.cardColors(containerColor = BlockColor),
//            modifier = Modifier
//                .height(150.dp)
//                .fillMaxWidth()
//                .padding(10.dp))
//
//        {
        if (error) {
            Text(
                text = authUiState?.errorMessage ?: "",
                color = warningColor
            )
        }
        Row(modifier = Modifier.padding(horizontal = 10.dp)) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background((InputBorderColor), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {

                Column(modifier = Modifier.padding(20.dp).padding(start = 2.dp)) {
                    Box(
                        modifier = Modifier
                            .background((backgroundColor), shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 1.dp)
                            .clip(RoundedCornerShape(15.dp))
                    ) {

                        OutlinedTextField(
                            value = authUiState?.loginEmail ?: "",
                            shape = RoundedCornerShape(15.dp),
                            onValueChange = {
                                authViewModel?.handleInputStateChanges("loginEmail", it)
                            },
                            label = {
                                Text(
                                    text = "Email",
                                    color = Color.Black,
                                    style = TextStyle(textAlign = TextAlign.Left)
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = null
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(backgroundColor)
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 1.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .height(60.dp),
                            textStyle = TextStyle(fontSize = 12.sp),
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(
                        modifier = Modifier
                            .background((backgroundColor), shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 1.dp)
                            .clip(RoundedCornerShape(15.dp))
//
//                            .width(200.dp)
//                            .height(60.dp),
                    ) {
                        OutlinedTextField(
                            value = authUiState?.loginPassword ?: "",
                            onValueChange = {
                                authViewModel?.handleInputStateChanges(
                                    "loginPassword",
                                    it
                                )
                            },

//                            shape = RoundedCornerShape(15.dp),
                            label = {
                                Text(
                                    text = "Password",
                                    color = LightGrey,
                                    style = TextStyle(textAlign = TextAlign.End)
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            ),

                            leadingIcon = {
                                Row(
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .padding(start = 2.dp),

                                    imageVector = Icons.Default.Lock,

                                    contentDescription = null
                                )
                            }},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier
//                                .padding(vertical = 6.dp)
//                                .padding(horizontal = 10.dp)
//                                .clip(RoundedCornerShape(15.dp))
                                .fillMaxWidth()
                                .background(backgroundColor)
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .padding(horizontal = 1.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .height(60.dp),

                            textStyle = TextStyle(fontSize = 12.sp),
                        )
                    }
                }
            }
        }
//

//        }

//        OutlinedTextField(
//            value = email,
//            shape = RoundedCornerShape(15.dp),
//            onValueChange = { email = it },
//            label = { Text(text = "Email") },
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Email,
//                    contentDescription = null
//                )
//            },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(5.dp)
//        )
//
//
////    Password
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            shape = RoundedCornerShape(15.dp),
//            label = { Text(text = "Password") },
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Lock,
//                    contentDescription = null
//                )
//            },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(5.dp)
//
//        )
        Spacer(modifier = Modifier.size(30.dp))
        Spacer(modifier = Modifier.size(30.dp))
        Button(
            onClick = { authViewModel?.loginUser(context) },
            modifier = Modifier
                .width(200.dp)
                .padding(4.dp), colors = ButtonDefaults.buttonColors(BlackButton)
        ) {
            Text(
                text = "Login",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp), color = ButtonTextColor
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            TextButton(onClick = { navToRegister.invoke() }) {
                Text(text = "Need an account?", fontSize = 18.sp, color = BlackButton)
            }
        }
    }
    SideEffect {
        val window = (context as? Activity)?.window
        window?.statusBarColor = Color.Black.toArgb()
        window?.decorView?.systemUiVisibility =
            view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewLoginScreen(){
    WildEyeChatAppTheme {
        LoginScreen(navToRegister = {})
    }
}