package com.example.wildeyechatapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.wildeyechatapp.ui.theme.BlackButton
import com.example.wildeyechatapp.ui.theme.BlockColor
import com.example.wildeyechatapp.ui.theme.ButtonTextColor
import com.example.wildeyechatapp.ui.theme.InputFieldColor
import com.example.wildeyechatapp.ui.theme.LightGrey
import com.example.wildeyechatapp.ui.theme.TitleColor
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.wildeyechatapp.ui.theme.BGcolor
import com.example.wildeyechatapp.ui.theme.warningColor
import com.example.wildeyechatapp.viewModels.AuthUiState
import com.example.wildeyechatapp.viewModels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(

    navToLogin:() -> Unit,
    navToHome:() -> Unit,
    authViewModel : AuthViewModel,
//    authViewModel: AuthViewModel? = null,
    modifier: Modifier = Modifier){
   val authUiState :AuthUiState? = authViewModel?.authUiState
    val context = LocalContext.current
    val error = authUiState?.errorMessage != null

    var passwordVisible by remember { mutableStateOf(false) }
    val passwordVisualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()


//state variables
//    var name by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var standNumber by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
    val titleColor = R.color.titleColor
//    Text(text="Login Screen working")modifier = Modifier
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
        Text(
            text="PLEASE FILL YOUR DETAILS BELOW",
            style = MaterialTheme.typography.displayMedium.copy(textAlign = TextAlign.Center),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = BlackButton,
            modifier = Modifier.padding(vertical = 15.dp)
        )
        Spacer(modifier = Modifier.size(7.dp))
        if (error) {
            Text(
                text = authUiState?.errorMessage ?: "",
                color = warningColor
            )
        }
        // NAME + CARD
     Card(colors = CardDefaults.cardColors(containerColor = Color.Black),
         modifier = Modifier.height(260.dp).fillMaxWidth().padding(10.dp))
      {
         OutlinedTextField(
             value = authUiState?.registerUsername ?: "",
             onValueChange = { authViewModel?.handleInputStateChanges("registerUsername", it)},
             shape = RoundedCornerShape(15.dp),
             label = {Text(text="Name",
                 color = LightGrey,
                 style = TextStyle(textAlign = TextAlign.Left))},
             leadingIcon = {
                 Icon(modifier = Modifier.size(20.dp),
                     imageVector = Icons.Default.Person,
                     contentDescription = null) },
             keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(horizontal = 4.dp, vertical = 4.dp) // Add padding here
//                                .padding(10.dp)
                 .height(50.dp),
             textStyle = TextStyle(fontSize = 10.sp, color = BGcolor))
// EMAIL
         OutlinedTextField(
             value = authUiState?.registerEmail ?: "",
             onValueChange = { authViewModel?.handleInputStateChanges("registerEmail", it)},
             shape = RoundedCornerShape(15.dp),
             label = {Text(text="Email",
                 color = LightGrey,
                 style = TextStyle(textAlign = TextAlign.End)
             )},
             leadingIcon = {
                 Icon(modifier = Modifier.size(20.dp),
                     imageVector = Icons.Default.Email,
                     contentDescription = null) },
             keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(horizontal = 4.dp, vertical = 4.dp) // Add padding here
//                                .padding(10.dp)
                 .height(50.dp),
             textStyle = TextStyle(fontSize = 10.sp, color = BGcolor))

//STAND NUMBER
         OutlinedTextField(
             value = authUiState?.registerStandNumber ?: "",
             onValueChange = { authViewModel?.handleInputStateChanges("registerStandNumber", it)},
             shape = RoundedCornerShape(15.dp),
             label = {Text(text="Stand Number",
                 color = LightGrey,
                 style = TextStyle(textAlign = TextAlign.End))},
             leadingIcon = {
                 Icon(modifier = Modifier.size(20.dp),
                     imageVector = Icons.Default.Home,
                     contentDescription = null) },
             keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(horizontal = 4.dp, vertical = 4.dp) // Add padding here
//                                .padding(10.dp)
                 .height(50.dp),
             textStyle = TextStyle(fontSize = 10.sp, color = BGcolor))
//PASSWORD
//         OutlinedTextField(
//             value = authUiState?.registerPassword ?: "",
//             onValueChange = { authViewModel?.handleInputStateChanges("registerPassword", it)},
//             shape = RoundedCornerShape(15.dp),
//             label = {Text(text="Password",
//                 color = LightGrey,
//                 style = TextStyle(textAlign = TextAlign.End))},
//             leadingIcon = {
//                 Icon(modifier = Modifier.size(20.dp),
//                     imageVector = Icons.Default.Lock,
//                     contentDescription = null) },
//             keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//             modifier = Modifier
//                 .fillMaxWidth()
//                 .padding(horizontal = 4.dp, vertical = 4.dp) // Add padding here
////                                .padding(10.dp)
//                 .height(50.dp),
//             textStyle = TextStyle(fontSize = 10.sp, color = BGcolor))
          OutlinedTextField(
              value = authUiState?.registerPassword ?: "",
              onValueChange = { authViewModel?.handleInputStateChanges("registerPassword", it) },
              shape = RoundedCornerShape(15.dp),
              label = {
                  Text(
                      text = "Password",
                      color = LightGrey,
                      style = TextStyle(textAlign = TextAlign.End)
                  )
              },
              leadingIcon = {
                  Icon(
                      modifier = Modifier.size(20.dp),
                      imageVector = Icons.Default.Lock,
                      contentDescription = null
                  )
              },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 4.dp, vertical = 4.dp)
                  .height(50.dp),
              textStyle = TextStyle(fontSize = 10.sp, color = BGcolor),
              visualTransformation = passwordVisualTransformation,
              trailingIcon = {
                  IconButton(
                      onClick = { passwordVisible = !passwordVisible },
                  ) {
                      Icon(
                          imageVector = if (passwordVisible) Icons.Outlined.Visibility
                          else Icons.Outlined.VisibilityOff,
                          contentDescription = "Toggle password visibility"
                      )
                  }
              }
          )

     }
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = { authViewModel.createNewUser(context) },
            modifier = Modifier
              .width(200.dp)
              .padding(4.dp), colors = ButtonDefaults.buttonColors( BlackButton)
        ) {
            Text(text = "REGISTER",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp), color = ButtonTextColor
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            TextButton(onClick = { navToLogin.invoke()}, ) {
                Text(text = "Have an account?",fontSize = 18.sp, color = BlackButton )
            }
        }
    }

    LaunchedEffect(key1 = authViewModel?.hasUser){
        if(authViewModel.hasUser ){
            navToHome.invoke()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewRegisterScreen(){
    WildEyeChatAppTheme {
        RegisterScreen(navToLogin = {},navToHome = {}, authViewModel = AuthViewModel())
    }
}