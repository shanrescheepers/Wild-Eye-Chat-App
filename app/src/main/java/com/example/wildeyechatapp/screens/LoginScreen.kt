package com.example.wildeyechatapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wildeyechatapp.R
import com.example.wildeyechatapp.ui.theme.WildEyeChatAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat.RegisterReceiverFlags
import com.example.wildeyechatapp.ui.theme.BlackButton
import com.example.wildeyechatapp.ui.theme.BlockColor
import com.example.wildeyechatapp.ui.theme.ButtonTextColor
import com.example.wildeyechatapp.ui.theme.InputFieldColor
import com.example.wildeyechatapp.ui.theme.LightGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LoginScreen(navToRegister:() -> Unit ,
                modifier: Modifier = Modifier){

//state variables
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val titleColor = R.color.titleColor

//    Text(text="Login Screen working")
    Column(
        modifier = Modifier.background(InputFieldColor)
            .fillMaxSize(),


        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .width(width = 260.dp)
                .align(alignment = Alignment.CenterHorizontally),

            )


//        Spacer(modifier = Modifier.size(10.dp))


        Text(
            text = "Please fill your details below",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = BlackButton
        )

        Spacer(modifier = Modifier.size(10.dp))
        Card(colors = CardDefaults.cardColors(containerColor = BlockColor),
            modifier = Modifier.height(130.dp).fillMaxWidth().padding(10.dp))

        {
            OutlinedTextField(
            value = email,
            shape = RoundedCornerShape(15.dp),
            onValueChange = { email = it },
                label = {Text(text="Email",
                    color = LightGrey,
                    style = TextStyle(textAlign = TextAlign.Left))},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 8.dp)
                    .height(50.dp),
                textStyle = TextStyle(fontSize = 12.sp),
        )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(15.dp),
                label = {Text(text="Password",
                    color = LightGrey,
                    style = TextStyle(textAlign = TextAlign.End))},
                leadingIcon = {
                    Icon(modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.Lock,
                        contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 8.dp)
                    .height(50.dp),
                textStyle = TextStyle(fontSize = 12.sp),

                )
        }

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
        Spacer(modifier = Modifier.size(30.dp))
        Spacer(modifier = Modifier.size(50.dp))
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .width(200.dp)
                .padding(4.dp), colors = ButtonDefaults.buttonColors(BlackButton)
        ) {
            Text(
                text = "Sign In",
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

}

@Preview(showSystemUi = true)
@Composable
fun PreviewLoginScreen(){
    WildEyeChatAppTheme {
        LoginScreen(navToRegister = {})
    }
}