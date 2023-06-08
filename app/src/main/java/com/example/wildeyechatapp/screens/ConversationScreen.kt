package com.example.wildeyechatapp.screens
import android.provider.Telephony.Sms.Conversations
import android.widget.HorizontalScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.wildeyechatapp.R.drawable.smalllogopng
import com.example.wildeyechatapp.models.ConversationPeople
import com.example.wildeyechatapp.ui.theme.InputFieldColor

@Composable
fun ConversationScreen(


    modifier: Modifier = Modifier

){
Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .background(InputFieldColor)
        .fillMaxSize()
        .fillMaxWidth()) {
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
            modifier = Modifier.width(100.dp).height(100.dp)


            )
        Spacer(modifier = Modifier.width(160.dp))
        
//        Search Icon
//        Image(painter = painterResource(id = R.drawable.search),
//            contentDescription = null,
//            modifier = Modifier
//                .padding(8.dp)
//        )

//        Profile Picture
        Image(painter = painterResource(id = R.drawable.pfp),
            contentDescription = null,
            modifier = Modifier.width(80.dp).height(80.dp)
                .padding(8.dp)
        )
    }

    Spacer(modifier = Modifier.size(20.dp))
//    Welcome Second row
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(text = "Welcome,",
            fontSize = 20.sp,
            modifier = Modifier
            .padding(horizontal = 10.dp)
        )

    }

//Third Row
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
    ){
        Text(text = "Signed In User",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
            .padding(horizontal = 10.dp)
        )
    }
    Spacer(modifier = Modifier.size(20.dp))

LazyRow(){
    items(10){
        item ->
        ConversationCard(ConversationPeople(
            name = "Shanre${item}",
            image = "https://miro.medium.com/v2/resize:fit:4800/format:webp/1*yKCRYzrnFkLivh_f8FtLfA.jpeg"))
    }
}
    Row(
        Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxSize()
            .height(100.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.Start,

    ) {
//Column(Modifier.verticalScroll(rememberScrollState()))

//        ConversationCard(ConversationPeople(
//            name = "Shanre",
//        ))
//        ConversationCard(ConversationPeople(
//            name = "Shanre",
//        ))
//        ConversationCard(ConversationPeople(
//            name = "Shanre",
//        ))
//        ConversationCard(ConversationPeople(
//            name = "Shanre",
//        ))
    }



    }
    Spacer(modifier = Modifier.size(20.dp))
}

@Composable
fun ConversationCard(
    conversationPeople: ConversationPeople,
    modifier: Modifier = Modifier) {

    Card(

        modifier = Modifier
            .padding(8.dp)
            .width(100.dp)
            .height(100.dp)
            .background(color = InputFieldColor),

        shape = RoundedCornerShape(80.dp),) {
        Column(modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){

            AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
                .data(conversationPeople.image)
                .crossfade(true).build()
                ,
                error= painterResource( R.drawable.ic_launcher_foreground),

                contentDescription = conversationPeople.name,
placeholder = painterResource(id = R.drawable.ic_launcher_foreground),

                modifier = Modifier
                    .width(80.dp)
                    .height(20.dp)
                    .background(color = InputFieldColor),
               alignment = Alignment.CenterStart,
                contentScale = ContentScale.Crop
                )

            Text(
                text= conversationPeople.name,
                modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
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