package com.example.teammanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.teammanagementapp.presentation.navigation.graphs.Graph
import com.example.teammanagementapp.presentation.navigation.graphs.RootNavigationGraph
import com.example.teammanagementapp.ui.theme.MyTheme
import com.example.teammanagementapp.presentation.googlesignin.GoogleAuthUiClient
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val startDestination = if (FirebaseAuth.getInstance().currentUser != null) {
                Graph.MAIN
            } else {
                Graph.AUTH
            }
            MyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Modifier.padding(innerPadding)
                    RootNavigationGraph(
                        startDestination = startDestination,
                        googleAuthUiClient = googleAuthUiClient
                    )
                }
            }
        }
    }
}
