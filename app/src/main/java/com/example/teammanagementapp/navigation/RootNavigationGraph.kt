package com.example.teammanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teammanagementapp.Screens

@Composable
fun RootNavigationGraph(startDestination: String) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = startDestination,
        route = Graph.ROOT
    ) {
        composable(Graph.AUTH) {
            authNavGraph(navController = rootNavController)
        }
        authNavGraph(navController = rootNavController)
        composable(Graph.MAIN) {
            mainNavGraph(navController = rootNavController)
        }
        mainNavGraph(navController = rootNavController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
}