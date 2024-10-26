package com.example.teammanagementapp.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teammanagementapp.presentation.googlesignin.GoogleAuthUiClient

@Composable
fun RootNavigationGraph(
    startDestination: String, googleAuthUiClient: GoogleAuthUiClient
) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = startDestination,
        route = Graph.ROOT
    ) {
        composable(Graph.AUTH) {
            authNavGraph(
                navController = rootNavController, googleAuthUiClient
            )
        }
        authNavGraph(navController = rootNavController, googleAuthUiClient)
        composable(Graph.MAIN) {
            mainNavGraph(
                navController = rootNavController, googleAuthUiClient
            )
        }
        mainNavGraph(
            navController = rootNavController, googleAuthUiClient
        )
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
}