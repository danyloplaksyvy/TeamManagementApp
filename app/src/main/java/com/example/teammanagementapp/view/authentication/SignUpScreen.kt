package com.example.teammanagementapp.view.authentication

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.teammanagementapp.Screens
import com.example.teammanagementapp.navigation.Graph
import com.example.teammanagementapp.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    val context = LocalContext.current

    val emailFieldState = remember { mutableStateOf("") }
    val passwordFieldState = remember { mutableStateOf("") }
    val confirmPasswordFieldState = remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val errorMessage by authViewModel.errorMessage.collectAsState()

    // Collect sign up result from ViewModel
    LaunchedEffect(key1 = authViewModel.signUpResult) {
        authViewModel.signUpResult.collect { isSuccess ->
            if (isSuccess) {
                navController.navigate(Graph.MAIN) {
                    popUpTo(Screens.SignUpScreen.name) { inclusive = true }
                    popUpTo(Screens.StartAuthScreen.name) { inclusive = true }
                }
            } else {
                errorMessage?.let { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Register",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            "Create your account",
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
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            value = passwordFieldState.value,
            onValueChange = { passwordFieldState.value = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Outlined.Password, "Password") },
            shape = RoundedCornerShape(36.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                        ""
                    )
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            value = confirmPasswordFieldState.value,
            onValueChange = { confirmPasswordFieldState.value = it },
            label = { Text("Confirm password") },
            leadingIcon = { Icon(Icons.Outlined.Password, "Confirm Password") },
            shape = RoundedCornerShape(36.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                        ""
                    )
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Button(
            onClick = {
                val email = emailFieldState.value.trim()
                val password = passwordFieldState.value.trim()
                val confirmPassword = confirmPasswordFieldState.value.trim()
                if (confirmPassword != password) {
                    Toast.makeText(context, "Passwords must be the same", Toast.LENGTH_LONG)
                        .show()
                }
                authViewModel.signUpUser(email, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) { Text("Sign up") }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Already have an account? ")
            TextButton(onClick = { navController.navigate(Screens.SignInScreen.name) }) { Text("Login") }
        }
    }
}