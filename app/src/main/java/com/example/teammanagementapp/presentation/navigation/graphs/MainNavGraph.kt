package com.example.teammanagementapp.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.teammanagementapp.Screens
import com.example.teammanagementapp.presentation.view.main.home.MainScreen
import com.example.teammanagementapp.presentation.view.main.profile.ProfileScreen
import com.example.teammanagementapp.presentation.googlesignin.GoogleAuthUiClient
import com.example.teammanagementapp.presentation.view.main.project.ProjectDetailsScreen
import com.google.firebase.auth.FirebaseAuth

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient
) {
    navigation(route = Graph.MAIN, startDestination = Screens.MainScreen.name) {
        composable(route = Screens.MainScreen.name) {
            MainScreen(
                onProfileNavigate = { navController.navigate(Screens.ProfileScreen.name) },
                onProjectClick = { projectId ->
                    navController.navigate("${Screens.ProjectDetailsScreen.name}/$projectId")
                }
            )
        }
        composable(route = Screens.ProjectDetailsScreen.name + "/{projectId}") { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")
            projectId?.let {
                ProjectDetailsScreen(projectId = it, onBackClick = { navController.popBackStack() })
            }
        }
//        composable(route = Screens.MainScreen.name) {
//            MainScreen(onProfileNavigate = {
//                navController.navigate(Screens.ProfileScreen.name)
//            }, onCreateProjectClick = {
//                navController.navigate(Screens.ProjectDetailsScreen.name)
//            })
//        }
        composable(route = Screens.ProfileScreen.name) {
            ProfileScreen(
                onSignOut = {
                    navController.navigate(Graph.AUTH)
                },
                googleAuthUiClient = googleAuthUiClient,
                currentUser = FirebaseAuth.getInstance().currentUser,
                onBackClick = { navController.popBackStack()}
            )
        }
//        composable(route = Screens.ProjectDetailsScreen.name) {
//            ProjectDetailsScreen()
//        }
    }
}