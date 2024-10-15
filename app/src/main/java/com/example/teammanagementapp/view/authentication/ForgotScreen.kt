package com.example.teammanagementapp.view.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.teammanagementapp.Screens

@Composable
fun ForgotScreen(navController: NavController) {
    val emailFieldState = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
    ) {
        Button(
            onClick = { navController.navigate(Screens.SignInScreen.name) },
            Modifier
                .align(alignment = Alignment.TopStart),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(49, 71, 58, 232)
            )
        ) {
            Icon(Icons.Default.ArrowBackIosNew, "Back")
            Text("Back")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp), // Padding to prevent overlap with IconButton
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Forgot Password?",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                "Enter your email below",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(16.dp)
            )
            OutlinedTextField(
                value = emailFieldState.value,
                onValueChange = { emailFieldState.value = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Outlined.Email, "Email") },
                shape = RoundedCornerShape(36.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                )
            )
            Button(
                onClick = { navController.navigate(Screens.StartAuthScreen.name) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) { Text("Reset password") }
        }
    }
}