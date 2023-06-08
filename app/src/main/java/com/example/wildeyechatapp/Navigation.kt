package com.example.wildeyechatapp
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wildeyechatapp.screens.ConversationScreen
import com.example.wildeyechatapp.screens.LoginScreen
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
            },
            authViewModel = authViewModel)
        }
//END
//        My ConversationsScreen
        composable(route = HomeRoutes.ConversationScreen.name){
            ConversationScreen()
        }

//        TODO: Create links to profile and chat screen(pass data
//        Stup auth to prevent access to specific screens
    }
}