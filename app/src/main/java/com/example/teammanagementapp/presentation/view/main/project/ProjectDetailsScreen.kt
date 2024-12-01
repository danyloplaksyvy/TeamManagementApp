package com.example.teammanagementapp.presentation.view.main.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teammanagementapp.domain.model.Project
import com.example.teammanagementapp.presentation.view.main.project.components.TableDataRow
import com.example.teammanagementapp.presentation.view.main.project.components.bottomsheet.AddTaskBottomSheet
import com.example.teammanagementapp.presentation.viewmodel.ProjectViewModel
import com.example.teammanagementapp.utils.NotImplementedAlertDialog
import com.example.teammanagementapp.utils.TableTitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailsScreen(
    projectId: String,
    projectViewModel: ProjectViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val project by projectViewModel.project.observeAsState()
    val tasks by projectViewModel.tasks.collectAsState()

    var showNotImplementedAlertDialog by remember { mutableStateOf(false) }
    var showAddTaskSheet by remember { mutableStateOf(false) }
    // Load project data when the screen is created
    LaunchedEffect(projectId) {
        projectViewModel.loadProject(projectId)
        projectViewModel.loadTasks(projectId)
    }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            project?.name ?: "No Name",
                            color = MaterialTheme.colorScheme.onPrimary,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onBackClick() }) {

                            Icon(
                                Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                            Text("Back", color = MaterialTheme.colorScheme.onPrimary)
                        }
                    },
                    actions = {
                        Button(
                            onClick = { showNotImplementedAlertDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                MaterialTheme.colorScheme.secondary.copy(alpha = 1f)
                            )
                        ) {
                            Text("Invite")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TableTitleText("Check", modifier = Modifier.weight(0.5f))
                    TableTitleText("Name", modifier = Modifier.weight(1f))
                    TableTitleText("Member", modifier = Modifier.weight(1f))
                    TableTitleText("Deadline", modifier = Modifier.weight(1f))
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddTaskSheet = true },
                containerColor = MaterialTheme.colorScheme.tertiary
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "New task")
                    Text("New task")
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                if (tasks.isEmpty()) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Top
                        ) {
                            Card(
                                modifier = Modifier.padding(16.dp),
                                elevation =CardDefaults.cardElevation(defaultElevation = 16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(
                                    "There is no tasks :)",
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }
                        }
                    }
                } else {
                    items(tasks) { task ->
                        TableDataRow(task)
                    }
                }
            }
        }
    }

    if (showNotImplementedAlertDialog) {
        NotImplementedAlertDialog(onConfirmation = { showNotImplementedAlertDialog = false },
            onDismissRequest = { showNotImplementedAlertDialog = false })
    }

    if (showAddTaskSheet) {
        AddTaskBottomSheet(
            onDismiss = { showAddTaskSheet = false },
            onCreateTask = { name, participants, deadline ->
                projectViewModel.addTask(projectId, name, participants, deadline)
                showAddTaskSheet = false
            }
        )
    }
}