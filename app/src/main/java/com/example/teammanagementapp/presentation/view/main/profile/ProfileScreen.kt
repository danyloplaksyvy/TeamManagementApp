package com.example.teammanagementapp.presentation.view.main.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.teammanagementapp.presentation.googlesignin.GoogleAuthUiClient
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onSignOut: () -> Unit,
    googleAuthUiClient: GoogleAuthUiClient,
    currentUser: FirebaseUser?,
    onBackClick: () -> Unit
) {

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
//            windowInsets = WindowInsets(0, 0, 0, 0),
            title = { Text("Profile Screen") },
            navigationIcon = {
                Row(modifier = Modifier.clip(RoundedCornerShape(12.dp)).clickable { onBackClick() }) {
                    Icon(Icons.Default.ArrowBackIosNew, "Back")
                    Text("Back")
                }
            })
    }) { innerPadding ->


        val scope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            currentUser?.let { user ->
                user.photoUrl?.let {
                    AsyncImage(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(shape = RoundedCornerShape(32.dp)),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Profile picture",
                        contentScale = ContentScale.Crop
                    )
                }
                user.displayName?.let { name ->
                    Text(
                        text = name, maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                user.email?.let { email ->
                    Text(
                        text = "Email: $email", maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Button(
                onClick = {
                    scope.launch {
                        try {
                            googleAuthUiClient.signOut()
                            onSignOut()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            if (e is CancellationException) throw e
                        }
                    }
                }, modifier = Modifier.padding(16.dp)
            ) {
                Text("Sign out")
            }
        }
    }
}