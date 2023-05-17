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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.wildeyechatapp.ui.theme.BlackButton
import com.example.wildeyechatapp.ui.theme.ButtonTextColor
import com.example.wildeyechatapp.ui.theme.LightGrey
import com.example.wildeyechatapp.ui.theme.TitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navToLogin:() -> Unit ,
                   modifier: Modifier = Modifier){
//state variables
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var standNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val titleColor = R.color.titleColor

//    Text(text="Login Screen working")
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .padding(top = 20.dp))





        Text(
            text="Please fill your details below",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = TitleColor,
        )

        Spacer(modifier = Modifier.size(8.dp))
        // NAME
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            shape = RoundedCornerShape(15.dp),
            label = {Text(text="Name", color = LightGrey)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
// EMAIL
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            shape = RoundedCornerShape(15.dp),
            label = {Text(text="Email", color = LightGrey)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email,
                    contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
//STAND NUMBER
        OutlinedTextField(
            value = standNumber,
            onValueChange = { standNumber = it },
            shape = RoundedCornerShape(15.dp),
            label = {Text(text="Stand Number", color = LightGrey)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Home,
                    contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
//PASSWORD
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            shape = RoundedCornerShape(15.dp),
            label = {Text(text="Password", color = LightGrey)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock,
                    contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)

        )
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .width(200.dp)
            .padding(4.dp), colors = ButtonDefaults.buttonColors( BlackButton)) {
            Text(text = "REGISTER",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp), color = ButtonTextColor)
        }


        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){

            TextButton(onClick = { navToLogin.invoke()}, ) {
                Text(text = "Have an account?",fontSize = 18.sp, color = BlackButton )
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewRegisterScreen(){
    WildEyeChatAppTheme {
        RegisterScreen(navToLogin = {})
    }
}