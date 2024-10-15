package com.example.teammanagementapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.teammanagementapp.Screens
import com.example.teammanagementapp.view.main.MainScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
) {
    navigation(route = Graph.MAIN, startDestination = Screens.MainScreen.name) {
        composable(route = Screens.MainScreen.name) {
            MainScreen()
        }
    }
}