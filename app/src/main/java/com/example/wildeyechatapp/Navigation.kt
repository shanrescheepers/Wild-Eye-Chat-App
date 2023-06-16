package com.example.wildeyechatapp
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wildeyechatapp.screens.ChatScreen
import com.example.wildeyechatapp.screens.ConversationScreen
import com.example.wildeyechatapp.screens.LoginScreen
import com.example.wildeyechatapp.screens.ProfileScreen
import com.example.wildeyechatapp.screens.RegisterScreen
import com.example.wildeyechatapp.viewModels.AuthViewModel

enum class AuthRoutes {
    Login,
    Register
}

enum class HomeRoutes{
    ConversationScreen,
    Chat,
    Profile
}
//gen navhost, ID navhost, it should use navcontroller
@SuppressLint("SuspiciousIndentation")
@Composable
fun Navigation(
    authViewModel: AuthViewModel,
    navController: NavHostController = rememberNavController()
) {

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { Log.d("Notification request", it.toString()) })

    val startingScreen = if(authViewModel.hasUser){
        HomeRoutes.ConversationScreen.name
    } else {
        AuthRoutes.Login.name
    }

    NavHost(
        navController = navController,
//        this will be splash
        startDestination = startingScreen){
//        define all navigatuon screens

//        My login screen
        composable(route = AuthRoutes.Login.name){
        LoginScreen(
            navToRegister = {
                navController.navigate(AuthRoutes.Register.name){
                 launchSingleTop = true
                    popUpTo(route = AuthRoutes.Login.name){
                        inclusive = true
                    }
                }
            },
            navToHome = {
                navController.navigate(HomeRoutes.ConversationScreen.name) {
                    launchSingleTop = true
                    popUpTo(route =AuthRoutes.Login.name){
                        inclusive = true
                    }
                }
            },
            authViewModel = authViewModel)
        }
        //        My Register screen
        composable(route = AuthRoutes.Register.name){
        RegisterScreen(
            navToLogin = {
                navController.navigate(AuthRoutes.Login.name){
                    launchSingleTop = true
                    popUpTo(route = AuthRoutes.Register.name){
                        inclusive = true
                    }
                }
            },

            navToHome = {
                navController.navigate(HomeRoutes.ConversationScreen.name) {
                    launchSingleTop = true
                    popUpTo(route =AuthRoutes.Register.name){
                        inclusive = true
                    }
                }
            },    authViewModel = authViewModel)
        }
//END
//        My ConversationsScreen
        composable(route = HomeRoutes.ConversationScreen.name){
            ConversationScreen(
                onNavToProfile = {
                        navController.navigate(HomeRoutes.Profile.name){
                            launchSingleTop = true
                        }
                },
//                click fun
                onNavToChat = { conversationId, chatName ->
                    navController.navigate("${HomeRoutes.Chat.name}/$conversationId/$chatName") {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(route = HomeRoutes.Profile.name){
            ProfileScreen(
                navOnLogOut = {
                    navController.navigate(AuthRoutes.Login.name) {
                        launchSingleTop = true
                        popUpTo(route = HomeRoutes.ConversationScreen.name){
                            inclusive = true
                        }
                    }
                }, navBack = {
                    navController.navigate(HomeRoutes.ConversationScreen.name){
                        launchSingleTop = true
                        popUpTo(route = HomeRoutes.ConversationScreen.name)
                    }
                }
            )
        }

        composable(route = "${HomeRoutes.Chat.name}/{chatId}/{chatName}",
            arguments = listOf(navArgument("chatId"){type = NavType.StringType; defaultValue = "chat1234"},navArgument("chatName"){type = NavType.StringType; defaultValue = "group"})
            ){
            ChatScreen(chatId =  it.arguments?.getString("chatId"), chatName = it.arguments?.getString("chatName")
                .toString())
        }
//        TODO: Create links to profile and chat screen(pass data
//        Stup auth to prevent access to specific screens


    }
    LaunchedEffect(key1 = permissionLauncher){
        Log.d("Launch permission request", "Yes!")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}