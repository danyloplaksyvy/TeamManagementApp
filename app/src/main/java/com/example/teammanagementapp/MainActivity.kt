package com.example.teammanagementapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.teammanagementapp.navigation.Graph
import com.example.teammanagementapp.navigation.RootNavigationGraph
import com.example.teammanagementapp.ui.theme.MyTheme
import com.example.teammanagementapp.ui.theme.TeamManagementAppTheme
import com.example.teammanagementapp.view.authentication.ForgotScreen
import com.example.teammanagementapp.view.authentication.SignInScreen
import com.example.teammanagementapp.view.authentication.SignUpScreen
import com.example.teammanagementapp.view.authentication.StartAuthScreen
import kotlinx.coroutines.CoroutineExceptionHandler

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Modifier.padding(innerPadding)
                    RootNavigationGraph(startDestination = Graph.AUTH)
                }
            }
        }
    }
}
