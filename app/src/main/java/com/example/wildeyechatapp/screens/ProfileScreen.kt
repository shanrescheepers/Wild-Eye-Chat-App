package com.example.wildeyechatapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wildeyechatapp.services.AuthService
import com.example.wildeyechatapp.ui.theme.BGcolor
import com.example.wildeyechatapp.ui.theme.LightGrey
import com.example.wildeyechatapp.ui.theme.WildEyeChatAppTheme
import com.example.wildeyechatapp.ui.theme.backgroundColor
import com.example.wildeyechatapp.viewModels.ProfileViewModel

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.example.wildeyechatapp.ui.theme.BlackButton
import com.example.wildeyechatapp.ui.theme.ButtonTextColor
import com.example.wildeyechatapp.ui.theme.warningColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
//    items: List<DropdownItem>,
//    selectedItem: DropdownItem?,
//    onItemSelected: (DropdownItem) -> Unit
    navOnLogOut: () -> Unit,
    navBack: ()-> Unit,
    modifier: Modifier = Modifier) {


    val currentUsername = remember { mutableStateOf("") }
    val newUsername = remember{ mutableStateOf("") }
    val newStand = remember{ mutableStateOf("") }
    val newEmail = remember{ mutableStateOf("") }
    val currentEmail = remember{ mutableStateOf("") }
    val currentPassword = remember{ mutableStateOf("") }
    val newImage = remember{ mutableStateOf("Option 1") }

//    LaunchedEffect(Unit){
//        Log.d("User", viewModel.currentUser?.username.toString())
//        newUsername.value = viewModel.currentUser?.username.toString() ?: ""
//
//    }

//    newUsername.value = viewModel.currentUser?.username ?: ""
//    newUsername.apply { viewModel.currentUser?.username }

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
    Column(modifier = Modifier.fillMaxWidth(),

      ) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .clickable {
                    navBack.invoke()
                },
        ){
           Row() {
               Icon(imageVector = Icons.Default.NavigateBefore , contentDescription = "back nav",modifier = Modifier.size(24.dp).padding(4.dp))
               Text(text ="Back")
           }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Welcome,",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )

                viewModel.currentUser?.let {
                    Text(text = it.username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ,style = TextStyle(
                            textAlign = TextAlign.Center
                        )

                    )
                }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "NOTE: If you select a Profile Icon, please fill in all the fields below for verification reasons",
            fontSize = 13.sp, color = Color.Black,modifier=Modifier.padding(8.dp),style = TextStyle(
                textAlign = TextAlign.Center
            ))
//Third Row
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start,
//        )
       Column(     modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
           ) {
           val options = listOf(
               "🐆" to "https://firebasestorage.googleapis.com/v0/b/wild-eye-chat-app.appspot.com/o/pfp_1.png?alt=media&token=f0aa2c7c-b4f3-44ce-8b10-4d1349e69646",
               "🐾" to "https://firebasestorage.googleapis.com/v0/b/wild-eye-chat-app.appspot.com/o/pfp_2.png?alt=media&token=edef79b2-8ce0-479c-bee5-5ad5c6d9bd27",
               "🦁" to "https://firebasestorage.googleapis.com/v0/b/wild-eye-chat-app.appspot.com/o/pfp_3.png?alt=media&token=87eaead0-669d-4879-8827-4b52aadfe834",
               "🦒" to "https://firebasestorage.googleapis.com/v0/b/wild-eye-chat-app.appspot.com/o/pfp_4.png?alt=media&token=7f3a82ba-53ff-4bb2-b404-5a32e2db67be",
               "🦅" to "https://firebasestorage.googleapis.com/v0/b/wild-eye-chat-app.appspot.com/o/pfp_5.png?alt=media&token=9da8a20d-1089-4977-ac1a-03177fe770dd")
           var expanded by remember { mutableStateOf(false) }
           var selectedOptionText by remember { mutableStateOf(options[0].first) }
// We want to react on tap/press on TextField to show menu
           Box(
               modifier = Modifier
                   .clip(RoundedCornerShape(8.dp))
                   .background(Color.White)
           ) {
               ExposedDropdownMenuBox(
                   expanded = expanded,
                   onExpandedChange = { expanded = !expanded },

                   ) {
                   TextField(
                       // The `menuAnchor` modifier must be passed to the text field for correctness.
                       modifier = Modifier.menuAnchor(),
                       readOnly = true,
                       value = selectedOptionText,
                       onValueChange = {},
                       label = { Text("Select/change Profile Icon") },
                       trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                       colors = ExposedDropdownMenuDefaults.textFieldColors(),
                   )
                   ExposedDropdownMenu(
                       expanded = expanded,
                       onDismissRequest = { expanded = false },
                   ) {
                       options.forEach { selectionOption ->
                           DropdownMenuItem(
                               text = {
                                   Row() {
                                       Text(selectionOption.first)
                                       AsyncImage(
                                           model = selectionOption.second,
                                           contentDescription = selectionOption.first
                                       )
                                   }
                               },
                               onClick = {
                                   selectedOptionText = selectionOption.first
                                   expanded = false
                                   newImage.value = selectionOption.second
                               },
                               contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                           )
                       }
                   }
               }
           }
           OutlinedTextField(
               value = newUsername.value,
               onValueChange = { value -> newUsername.value = value },
               shape = RoundedCornerShape(15.dp),
               label = {
                   Text(
                       text = "Re-enter username",
                       color = Color.Black,
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
               textStyle = TextStyle(fontSize = 10.sp, color = Color.Black)
           )
           OutlinedTextField(
               value = newStand.value,
               onValueChange = { value -> newStand.value = value },
               shape = RoundedCornerShape(15.dp),
               label = {
                   Text(
                       text = "Re-enter stand number",
                       color = Color.Black,
                       style = TextStyle(textAlign = TextAlign.Left)
                   )
               },
               leadingIcon = {
                   Icon(
                       modifier = Modifier.size(20.dp),
                       imageVector = Icons.Default.Home,
                       contentDescription = null
                   )
               },
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 4.dp, vertical = 4.dp) // Add padding here
                   .height(50.dp),
               textStyle = TextStyle(fontSize = 10.sp, color = Color.Black)
           )
//        Current Email
//           Text(text = "NOTE: If you edit your email, please fill in all the fields below",
//               fontSize = 13.sp, color = Color.Black,
//               modifier=Modifier.padding(8.dp),style = TextStyle(
//                   textAlign = TextAlign.Center
//               ))
           OutlinedTextField(
               value = currentEmail.value,
               onValueChange = { value -> currentEmail.value = value },
               shape = RoundedCornerShape(15.dp),
               label = {
                   Text(
                       text = "Current Email",
                       color = LightGrey,
                       style = TextStyle(textAlign = TextAlign.Left)
                   )
               },
               leadingIcon = {
                   Icon(
                       modifier = Modifier.size(20.dp),
                       imageVector = Icons.Default.Email,
                       contentDescription = null
                   )
               },
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 4.dp, vertical = 4.dp) // Add padding here
                   .height(50.dp),
               textStyle = TextStyle(fontSize = 10.sp, color = BGcolor)
           )
//        NEW EMAIL
           OutlinedTextField(
               value = newEmail.value,
               onValueChange = { value -> newEmail.value = value },
               shape = RoundedCornerShape(15.dp),
               label = {
                   Text(
                       text = "New Email",
                       color = Color.Black,
                       style = TextStyle(textAlign = TextAlign.Left)
                   )
               },
               leadingIcon = {
                   Icon(
                       modifier = Modifier.size(20.dp),
                       imageVector = Icons.Default.Email,
                       contentDescription = null
                   )
               },
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 4.dp, vertical = 4.dp) // Add padding here
                   .height(50.dp),
               textStyle = TextStyle(fontSize = 10.sp, color = Color.Black)
           )

//        Password
           OutlinedTextField(
               value = currentPassword.value,
               onValueChange = { value -> currentPassword.value = value },
               shape = RoundedCornerShape(15.dp),
               label = {
                   Text(
                       text = "Password",
                       color = Color.Black,
                       style = TextStyle(textAlign = TextAlign.Left)
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
                   .padding(horizontal = 4.dp, vertical = 4.dp) // Add padding here
                   .height(50.dp),
               textStyle = TextStyle(fontSize = 10.sp, color = Color.Black)
           )
// EMAIL


//STAND NUMBER



//        ////////
//       TODO: pfp image



       }
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        val context = LocalContext.current
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {AuthService().signOutUser(); navOnLogOut.invoke()},
                modifier = Modifier.padding(4.dp), colors = ButtonDefaults.buttonColors(BlackButton)) {
                Text(text = "Logout", fontSize = 18.sp,
                        modifier = Modifier.padding(8.dp), color = ButtonTextColor
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                val uid = viewModel.currentUserId
                if(newUsername.value.toString() == "" &&  currentEmail.value.toString() == "" &&
                    newImage.value.toString() == "" && newStand.value.toString() == "" && currentPassword.value.toString() == ""){
                    Toast.makeText(context, "You cannot UPDATE if you haven't entered any field to update. Try Again", Toast.LENGTH_SHORT)
                        .show()
                }else{
                    if (newUsername.value.toString() != "" && newImage.value.toString() !="" && newStand.value.toString() != ""){
                        viewModel.updateUser(uid, newUsername.value.toString(), newImage.value.toString(), newEmail.value.toString(), newStand.value.toString()) { success ->
                            if (success) {
                                Toast.makeText(context, "Profile update", Toast.LENGTH_SHORT).show()
//                            navigation.goBack()
                                // Update successful, handle the desired action
                            } else {
                                // Update failed, handle the error
                                Toast.makeText(context, "Profile failed to update", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else{
                        Toast.makeText(context, "Please fill all of the fields", Toast.LENGTH_SHORT).show()

                    }
                    if (newEmail.value.toString() != "") {
                        viewModel.updateEmail(
                            currentEmail.value.toString(),
                            currentPassword.value.toString(),
                            newEmail.value.toString()
                        ) {success ->
                            if (success) {
                                Toast.makeText(context, "Profile Email update", Toast.LENGTH_SHORT)
                                    .show()
//                            navigation.goBack()
                                // Update successful, handle the desired action
                            } else {
                                // Update failed, handle the error
                                Toast.makeText(
                                    context,
                                    "Profile Email failed to update",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        navBack.invoke()
                    }



                }



            },
                modifier = Modifier.padding(4.dp), colors = ButtonDefaults.buttonColors(warningColor)) {
                Text(text = "Update", fontSize = 18.sp, color = Color.Black,modifier=Modifier.padding(8.dp))
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