package com.example.wildeyechatapp.screens

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wildeyechatapp.R
import com.example.wildeyechatapp.ui.theme.InputBorderColor
import com.example.wildeyechatapp.ui.theme.InputFieldColor
import com.example.wildeyechatapp.ui.theme.WildEyeChatAppTheme
import com.example.wildeyechatapp.viewModels.ConversationsViewModel
import com.example.wildeyechatapp.models.Message
import com.example.wildeyechatapp.ui.theme.BGcolor
import com.example.wildeyechatapp.viewModels.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel(),
    chatId : String?,
    modifier: Modifier = Modifier
) {

    var newMessage by remember {
      mutableStateOf("")
    }
var isChatIdNotBlank = chatId.isNullOrBlank()

  val  currentUserFrom = "Shanre"

val dummyData = listOf<Message>(
    Message(fromUserId = "Shanre",from = "Shanre", message = " Your last message Your last message Your last message"),
    Message(fromUserId = "Leo",from = "Leo", message = " Hi Daar"),
    Message(fromUserId = "Shanre",from = "Shanre", message = " Hi"),
    Message(fromUserId = "Leo",from = "Leo", message = " Hi Daar"),
    Message(fromUserId = "Shanre",from = "Shanre", message = " Hi"),
    Message(fromUserId = "Leo",from = "Leo", message = " Hi Daar"),
    Message(fromUserId = "Shanre",from = "Shanre", message = " Hi"),
    Message(fromUserId = "Leo",from = "Leo", message = " Hi Daar"),
)

    LaunchedEffect(key1 = Unit){
        if (!isChatIdNotBlank){
            viewModel.getRealTimeMessages(chatId?:"")
            Log.d("Launch effect attempt", "")
        } else {
            Log.d("Launch effect fail","")
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = chatId ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp)
        )
//Lazycol
    LazyColumn(modifier = modifier.weight(1f),
    reverseLayout =  true){
        items(dummyData){message->
            if (currentUserFrom ==message.fromUserId){
                MessageToBubble(message)
            } else {
                MessageFromBubble(message)
            }
        }
    }


        Row(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            OutlinedTextField(
                value = newMessage,
                onValueChange = { newMessage = it},
                label = { Text("New Message") }
            )

            Spacer(modifier = modifier.size(8.dp))

            Button(
                onClick = {
                          viewModel.sendNewMessage(newMessage, chatId?:"");
                            newMessage = ""
                },
                modifier = modifier.height(50.dp),
            ) {
                Icon(painter = painterResource(id = R.drawable.pfp), contentDescription = null)
            }

        }
    }
}

@Composable
fun MessageFromBubble(
    message: Message, modifier: Modifier = Modifier
){
Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.Bottom) {
    Image(
//        USER IMAGE
        painter = painterResource(id = R.drawable.pfp),

        contentDescription = null,
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(InputFieldColor)
    )

    Column(modifier = modifier.padding(start = 8.dp, end = 16.dp)) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    InputBorderColor, shape = RoundedCornerShape(
                        topStart = 15.dp, topEnd = 15.dp, bottomEnd = 15.dp
                    )
                )
        ) {
            Text(
                text = message.from,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = modifier.padding(start = 10.dp, top = 10.dp)
            )
            Text(
                text = message.message,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = modifier.padding(start = 10.dp, top = 2.dp)
            )
            Text(
                text = message.timestamp.toDate().toString(),
                color = Color.LightGray,
                modifier = modifier
                    .padding(all = 10.dp)
                    .align(Alignment.End)
            )
        }
        }
    }
}

@Composable
fun MessageToBubble(
    message: Message, modifier: Modifier = Modifier
){
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.Bottom) {


        Column(modifier = modifier.padding(start = 16.dp, end = 8.dp)) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        BGcolor, shape = RoundedCornerShape(
                            topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp
                        )
                    )
            ) {

                Text(
                    text = message.message,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = modifier.padding(start = 10.dp, top = 2.dp)
                )
                Text(
                    text = message.timestamp.toDate().toString(),
                    color = Color.Gray,
                    modifier = modifier
                        .padding(all = 10.dp)
                        .align(Alignment.End)
                )
            }

        }
//        Image(
////        USER IMAGE
//            painter = painterResource(id = R.drawable.pfp),
//
//            contentDescription = null,
//            modifier = modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .background(InputFieldColor)
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun prevChatScreen() {
    WildEyeChatAppTheme {
//        RegisterScreen()
//       Navigation()
//        LoginScreen()
        ChatScreen(chatId = "chat1234")

    }
}