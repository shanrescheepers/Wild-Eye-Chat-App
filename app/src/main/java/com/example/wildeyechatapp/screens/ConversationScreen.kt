package com.example.wildeyechatapp.screens
import android.provider.Telephony.Sms.Conversations
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
import com.example.wildeyechatapp.R.drawable.smalllogopng
import com.example.wildeyechatapp.ui.theme.InputFieldColor

@Composable
fun ConversationScreen(modifier: Modifier = Modifier){
Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.background(InputFieldColor)
        .fillMaxSize()) {
//    first row
    Spacer(modifier = Modifier.size(20.dp))

    Row(
    modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
//        horizontalArrangement = Arrangement.Center
    ) {

//        Eagle LOGO
        Image(painter = painterResource(id = R.drawable.eagle),
            contentDescription = null,
            modifier = Modifier
                ,

            )
        Spacer(modifier = Modifier.width(180.dp))
        
//        Search Icon
        Image(painter = painterResource(id = R.drawable.search),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
        )

//        Profile Picture
        Image(painter = painterResource(id = R.drawable.pfp),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
        )
    }

    Spacer(modifier = Modifier.size(20.dp))
//    Welcome Second row
    Row() {
        Text(text = "Hi")
    }
//Third Row
    Spacer(modifier = Modifier.size(20.dp))
    Row() {
        Text(text = "Hi")
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Hi")
    }
}
}

@Preview(showSystemUi = true)
@Composable
fun PreviewConversationScreen(){
    WildEyeChatAppTheme {
        ConversationScreen()
    }
}