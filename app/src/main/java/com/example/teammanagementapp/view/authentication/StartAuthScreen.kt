package com.example.teammanagementapp.view.authentication

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.teammanagementapp.R
import com.example.teammanagementapp.Screens

@Composable
fun StartAuthScreen(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(R.drawable.appbg), "")
            Text("TManage", fontSize = 48.sp, fontStyle = FontStyle.Normal, modifier = Modifier.padding(32.dp))
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(
                onClick = { navController.navigate(Screens.SignInScreen.name)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) { Text("Log In") }
            Button(
                onClick = {navController.navigate(Screens.SignUpScreen.name)}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) { Text("Sign Up") }
            Text(text = "---------- or ----------", modifier = Modifier
                .padding(vertical = 16.dp))
            Button(
                onClick = {}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) { Text("Continue with Google") }
        }
    }