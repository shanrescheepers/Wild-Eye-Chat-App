package com.example.wildeyechatapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wildeyechatapp.services.AuthService
import com.example.wildeyechatapp.ui.theme.BGcolor
import com.example.wildeyechatapp.ui.theme.LightGrey
import com.example.wildeyechatapp.ui.theme.WildEyeChatAppTheme
import com.example.wildeyechatapp.ui.theme.backgroundColor
import com.example.wildeyechatapp.viewModels.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),

    navOnLogOut: () -> Unit,
    navBack: ()-> Unit,
    modifier: Modifier = Modifier) {
//@Composable
//fun ProfileScreen() {
    val currentUsername = remember { mutableStateOf("") }
    val newUsername = remember{ mutableStateOf("") }
//    newUsername.value = viewModel.currentUser?.username ?: ""
//
//    Column(
//        modifier = Modifier.padding(16.dp)
//    ) {
//        TextField(
//            value = newUsername.value,
//            onValueChange = { value -> newUsername.value = value },
//            label = { Text("Enter your username") }
//        )
//        Button(
//            onClick = {
//                val uid = FirebaseAuth.getInstance().currentUser?.uid
//                if (uid != null) {
//                    val db = Firebase.firestore
//                    val userRef = db.collection("users").document(uid)
//
//                    userRef.update("username", newUsername.value)
//                        .addOnSuccessListener {
//                            currentUsername.value = newUsername.value
//                            Toast.makeText(
//                                LocalContext.current,
//                                "Username updated successfully",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                        .addOnFailureListener {
//                            Toast.makeText(
//                                LocalContext.current,
//                                "Failed to update username",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                }
//            }
//        ) {
//            Text("Update Username")
//        }
//    }
//}
    Column() {
        Box(
            modifier
                .padding(5.dp)
                .background(backgroundColor)
                .clickable {
                    navBack.invoke()
                }){
            Text(text = "<- Back")
        }
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
            viewModel.currentUser?.let {
                Text(text = it.username,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                )
            }
        }
        OutlinedTextField(
            value = newUsername.value,
            onValueChange = { value -> newUsername.value = value },
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(
                    text = "Edit username",
                    color = LightGrey,
                    style = TextStyle(textAlign = TextAlign.Left)
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp) // Add padding here
                .height(50.dp),
            textStyle = TextStyle(fontSize = 10.sp, color = BGcolor)
        )

// EMAIL


//STAND NUMBER



//        ////////
//       TODO: pfp image

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Button(onClick = {AuthService().signOutUser(); navOnLogOut.invoke()}) {
                Text(text = "Logout", fontSize = 18.sp, color = BGcolor)
            }
            Button(onClick = {
                val uid = viewModel.currentUserId

                viewModel.updateUser(uid, newUsername.value.toString()) { success ->
                    if (success) {
                        // Update successful, handle the desired action
                    } else {
                        // Update failed, handle the error
                    }
                }
            }) {
                Text(text = "Update", fontSize = 18.sp, color = BGcolor)
            }
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