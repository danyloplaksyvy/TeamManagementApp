package com.example.teammanagementapp.presentation.view.main.home

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Group
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teammanagementapp.domain.model.Project
import com.example.teammanagementapp.presentation.view.main.home.components.ProjectCard
import com.example.teammanagementapp.presentation.view.main.home.components.bottomsheet.AddProjectBottomSheet
import com.example.teammanagementapp.presentation.view.main.home.components.bottomsheet.ManagingProjectAlertDialog
import com.example.teammanagementapp.presentation.viewmodel.ProjectViewModel
import com.example.teammanagementapp.utils.NotImplementedAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    projectViewModel: ProjectViewModel = viewModel(),
    onProfileNavigate: () -> Unit,
    onProjectClick: (String) -> Unit
) {
    val projects by projectViewModel.projects.collectAsStateWithLifecycle()
    // State to control showing the bottom sheet
    var showAddProjectBottomSheet by remember { mutableStateOf(false) }
    var showNotImplementedUIAlertDialog by remember { mutableStateOf(false) }

    var showManagingProjectAlertDialog by remember { mutableStateOf(false) }
    var selectedProject by remember { mutableStateOf<Project?>(null) }

    var projectNameTextField by remember { mutableStateOf("") }
    var descriptionTextField by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Your projects(${projects.count()})") },
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
                showAddProjectBottomSheet = true
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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Projects list(scrollable)
                LazyColumn {
                    items(projects) { project ->
                        ProjectCard(
                            project = project,
                            onProjectClick = { onProjectClick(project.id) },
                            onProjectLongPress = {
                                selectedProject = it
                                showManagingProjectAlertDialog = true
                            })
                    }
                }
            }
        }
    }

    // Add Project Bottom Sheet
    if (showAddProjectBottomSheet) {
        AddProjectBottomSheet(
            projectName = projectNameTextField,
            description = descriptionTextField,
            onProjectNameChange = { projectNameTextField = it },
            onDescriptionChange = { descriptionTextField = it },
            onDismissRequest = {
                showAddProjectBottomSheet = false
                projectNameTextField = ""
                descriptionTextField = ""
                selectedProject = null
            },
            onCreateClick = { name, description ->
                if (selectedProject != null) {
                    projectViewModel.updateProject(
                        projectId = selectedProject!!.id,
                        name = name,
                        description = description
                    )
                    selectedProject = null
                } else {
                    projectViewModel.addProject(name, description)
                }
                showAddProjectBottomSheet = false
                projectNameTextField = ""
                descriptionTextField = ""
            },
            onParticipantsClick = { showNotImplementedUIAlertDialog = true }
        )
    }

    if (showNotImplementedUIAlertDialog) {
        NotImplementedAlertDialog(
            onDismissRequest = { showNotImplementedUIAlertDialog = false },
            onConfirmation = { showNotImplementedUIAlertDialog = false })
    }

    if (showManagingProjectAlertDialog) {
        ManagingProjectAlertDialog(
            onDismissRequest = {
                showManagingProjectAlertDialog = false
                selectedProject = null
            },
            onEditClick = {
                showManagingProjectAlertDialog = false
                selectedProject?.let {
                    projectNameTextField = it.name
                    descriptionTextField = it.description ?: ""
                    showAddProjectBottomSheet = true
                }

            }, onDeleteClick = {
                showManagingProjectAlertDialog = false
                selectedProject?.let {
                    projectViewModel.deleteProject(it.id)
                    selectedProject = null
                }
            })
    }
}