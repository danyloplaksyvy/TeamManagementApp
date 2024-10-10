package com.example.teammanagementapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.teammanagementapp.Screens
import com.example.teammanagementapp.view.authentication.ForgotScreen
import com.example.teammanagementapp.view.authentication.SignInScreen
import com.example.teammanagementapp.view.authentication.SignUpScreen
import com.example.teammanagementapp.view.authentication.StartAuthScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
) {
    navigation(route = Graph.AUTH, startDestination = Screens.StartAuthScreen.name) {
        composable(route = Screens.StartAuthScreen.name) {
            StartAuthScreen(navController)
        }
        composable(route = Screens.SignUpScreen.name) {
            SignUpScreen(navController)
        }
        composable(route = Screens.SignInScreen.name) {
            SignInScreen(navController)
        }
        composable(route = Screens.ForgotScreen.name) {
            ForgotScreen(navController)
        }
    }
}