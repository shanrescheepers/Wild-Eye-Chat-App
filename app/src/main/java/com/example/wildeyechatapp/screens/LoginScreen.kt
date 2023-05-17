package com.example.wildeyechatapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.core.content.ContextCompat.RegisterReceiverFlags

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LoginScreen(navToRegister:() -> Unit ,
                modifier: Modifier = Modifier){

//state variables
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val titleColor = R.color.titleColor

//    Text(text="Login Screen working")
    Column(modifier = Modifier
    .fillMaxSize()
    .padding(20.dp),

horizontalAlignment = Alignment.CenterHorizontally
    ) {
Image(painter = painterResource(id = R.drawable.logo),
    contentDescription = null,
    modifier = Modifier.size(150.dp))


Spacer(modifier = Modifier.size(10.dp))


    Text(
        text="Please fill your details below",
    style = MaterialTheme.typography.displaySmall,
    fontSize = 24.sp,
    fontWeight = FontWeight.Medium,
    color = MaterialTheme.colorScheme.primary,
    )

    Spacer(modifier = Modifier.size(20.dp))

    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
    label = {Text(text="Email")},
    leadingIcon = {
        Icon(imageVector = Icons.Default.Email,
            contentDescription = null)
    },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))



//    Password

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = {Text(text="Password")},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock,
                contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)

    )
    Spacer(modifier = Modifier.size(30.dp))
    Button(onClick = { /*TODO*/ }, modifier = Modifier
        .width(200.dp)
        .padding(4.dp)) {
        Text(text = "Sign In",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp))
    }

    Spacer(modifier = Modifier.size(10.dp))

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){

        TextButton(onClick = { navToRegister.invoke()}) {
            Text(text = "Need an account?",fontSize = 18.sp, )
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