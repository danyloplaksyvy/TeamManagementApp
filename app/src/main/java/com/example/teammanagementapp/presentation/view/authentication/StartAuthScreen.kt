package com.example.teammanagementapp.presentation.view.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teammanagementapp.R
import com.example.teammanagementapp.presentation.googlesignin.GoogleAuthUiClient
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

@Composable
fun StartAuthScreen(
    onSignInNavigate: () -> Unit,
    onSignUpNavigate: () -> Unit,
    onSignInWithGoogle: () -> Unit,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.appbg), "")
        Text(
            "TManage",
            fontSize = 48.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.padding(32.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(
            onClick = onSignInNavigate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) { Text("Log In") }
        Button(
            onClick = onSignUpNavigate, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) { Text("Sign Up") }
        Text(
            text = "---------- or ----------", modifier = Modifier
                .padding(vertical = 16.dp)
        )
        Button(
            onClick = {
                scope.launch {
                    try {
                        val isSignInSuccessful = googleAuthUiClient.signInWithCredentials()
                        if (isSignInSuccessful) {
                            onSignInWithGoogle()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        if (e is CancellationException) throw e
                    }
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) { Text("Continue with Google") }
    }
}