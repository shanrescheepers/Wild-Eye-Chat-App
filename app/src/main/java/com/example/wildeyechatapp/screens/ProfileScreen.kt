package com.example.wildeyechatapp.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage

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
//@Composable
//fun ProfileScreen() {
    val currentUsername = remember { mutableStateOf("") }
    val newUsername = remember{ mutableStateOf("") }
    val newImage = remember{ mutableStateOf("Option 1") }

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



        val options = listOf(
            "Option 1" to "https://firebasestorage.googleapis.com/v0/b/wild-eye-chat-app.appspot.com/o/default_profile.png?alt=media&token=5283c9b7-e4a9-4423-b8c0-59ba08f9e61f",
            "Option 2" to "https://firebasestorage.googleapis.com/v0/b/wild-eye-chat-app.appspot.com/o/default_profile.png?alt=media&token=5283c9b7-e4a9-4423-b8c0-59ba08f9e61f",
            "Option 3" to "https://firebasestorage.googleapis.com/v0/b/wild-eye-chat-app.appspot.com/o/management_icon.png?alt=media&token=bf914656-0900-432e-a832-7f9b5e2068f5",
            "Option 4" to "Option 4",
            "Option 5" to "Option 5")
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0].first) }
// We want to react on tap/press on TextField to show menu
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
                label = { Text("Label") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.first)
                               AsyncImage(model = selectionOption.second, contentDescription = selectionOption.first)},
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
        val context = LocalContext.current
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
                if (newUsername.value.toString() != ""){
                    viewModel.updateUser(uid, newUsername.value.toString(), newImage.value.toString()) { success ->
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
                    Toast.makeText(context, "Fill all of the fields", Toast.LENGTH_SHORT).show()

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