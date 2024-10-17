package com.example.teammanagementapp.presentation.view.main.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onProfileNavigate: () -> Unit,
    onCreateProjectClick: () -> Unit
) {
    // State to control showing the bottom sheet
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val projectNameTextField = remember { mutableStateOf("") }
    val descriptionTextField = remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Your projects(1)") },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
            actions = {
                IconButton(onClick = onProfileNavigate) {
                    Icon(
                        Icons.Outlined.Settings,
                        "Profile"
                    )
                }
            }
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {
                showBottomSheet = true
            },
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ) { Icon(Icons.Outlined.Add, "Add Project") }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn {
                items(10) {
                    ProjectCard()
                }
            }
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxSize(),
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                BasicTextField(
                    value = projectNameTextField.value,
                    onValueChange = { projectNameTextField.value = it},
                    maxLines = 1,
                    modifier = Modifier.padding(16.dp),
                    decorationBox = { innerTextField ->
                        Box(modifier = Modifier.fillMaxWidth()) {
                            if (projectNameTextField.value.isEmpty()){
                                Text(
                                    "Project's name...",
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                    })
                Text(
                    "Project access",
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(Icons.Outlined.Group, "Team")
                    Text("Choose team", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
                    Icon(Icons.AutoMirrored.Outlined.KeyboardArrowRight, "ArrowRight")
                }
                BasicTextField(
                    value = descriptionTextField.value,
                    onValueChange = { descriptionTextField.value = it},
                    minLines = 5,
                    maxLines = 5,
                    modifier = Modifier.padding(16.dp),
                    decorationBox = { innerTextField ->
                        Box(modifier = Modifier.fillMaxWidth()) {
                            if (descriptionTextField.value.isEmpty()) {
                                Text(
                                    "Description...",
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                    })
                Button(onClick = onCreateProjectClick, modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Text("Create")
                }

            }
        }
    }
}