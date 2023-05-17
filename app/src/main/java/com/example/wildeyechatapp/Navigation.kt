package com.example.wildeyechatapp
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wildeyechatapp.screens.LoginScreen
import com.example.wildeyechatapp.screens.RegisterScreen

enum class AuthRoutes {
    Login,
    Register
}

//gen navhost, ID navhost, it should use navcontroller
@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController,
//        this will be splash
        startDestination = AuthRoutes.Login.name){
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
            }
        ) }
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
    }
)
        }

    }
}