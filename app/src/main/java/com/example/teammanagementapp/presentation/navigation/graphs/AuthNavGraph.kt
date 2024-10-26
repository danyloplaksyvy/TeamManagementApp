package com.example.teammanagementapp.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.teammanagementapp.Screens
import com.example.teammanagementapp.presentation.view.authentication.ForgotScreen
import com.example.teammanagementapp.presentation.view.authentication.SignInScreen
import com.example.teammanagementapp.presentation.view.authentication.SignUpScreen
import com.example.teammanagementapp.presentation.view.authentication.StartAuthScreen
import com.example.teammanagementapp.presentation.googlesignin.GoogleAuthUiClient

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient
) {
    navigation(route = Graph.AUTH, startDestination = Screens.StartAuthScreen.name) {
        composable(route = Screens.StartAuthScreen.name) {
            StartAuthScreen(onSignInNavigate = {
                navController.navigate(Screens.SignInScreen.name)
            }, onSignUpNavigate = {
                navController.navigate(Screens.SignUpScreen.name)
            }, onSignInWithGoogle = {
                navController.navigate(Graph.MAIN)
            }, googleAuthUiClient = googleAuthUiClient
            )
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