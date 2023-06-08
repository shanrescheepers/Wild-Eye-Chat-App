package com.example.wildeyechatapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wildeyechatapp.services.AuthService
import com.example.wildeyechatapp.ui.theme.BlackButton
import com.example.wildeyechatapp.ui.theme.WildEyeChatAppTheme
import com.example.wildeyechatapp.ui.theme.backgroundColor

@Composable
fun ProfileScreen(
    navOnLogOut: () -> Unit,
    navBack: ()-> Unit,
    modifier: Modifier = Modifier){

    Column() {
        Text(text = "My Profile")

//       TODO: pfp image

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Button(onClick = {AuthService().signOutUser(); navOnLogOut.invoke()}) {
                Text(text = "Logout", fontSize = 18.sp, color = BlackButton)
            }
        }
        Box(
            modifier
                .padding(5.dp)
                .background(backgroundColor)
                .clickable {
                    navBack.invoke()
                }){
            Text(text = "<- Back")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewProfileScreen(){
    WildEyeChatAppTheme {
        ProfileScreen(navOnLogOut = {}, navBack = {})
    }
}