package com.example.teammanagementapp.presentation.view.main.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProjectDetailsScreen() {
//    Scaffold(topBar = {
//        TopAppBar(
//            title = { Text("") },
//            navigationIcon = {
//                Row(
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(16.dp))
//                        .clickable { }
//                        .padding(16.dp)
//                ) {
//                    Icon(Icons.Default.ArrowBackIosNew, "Back To Projects")
//                    Text("Projects")
//                }
//            })
//    }) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
//        ) {
//            LazyColumn {
//                item {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text("Test")
//                        Button(onClick = {}) {
//                            Text("Invite")
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailsScreen(
//    projectName: String,
//    onBackClick: () -> Unit,
//    onInviteClick: () -> Unit,
//    onNewTaskClick: () -> Unit,
//    tasks: List<Task>
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Test", color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
                        )
                    ) {
                        Text("Invite")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.tertiary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "New task")
                Text("New task", modifier = Modifier.padding(start = 8.dp))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
//            ProjectTaskTable(tasks = tasks)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { /* Update task completion */ }
                )
                Text("Test")
                Text("Alice, John, Kirk")
                Text("20.11 - 12 days")
            }
        }
    }
}

//@Composable
//fun ProjectTaskTable(tasks: List<Task>) {
//    // Header Row
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.surface)
//            .padding(horizontal = 8.dp, vertical = 4.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text("Name", fontWeight = FontWeight.Bold)
//        Text("Responsible", fontWeight = FontWeight.Bold)
//        Text("Deadline", fontWeight = FontWeight.Bold)
//    }
//
//    // Task Rows
//    tasks.forEach { task ->
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 8.dp, vertical = 4.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Checkbox(
//                checked = task.isCompleted,
//                onCheckedChange = { /* Update task completion */ }
//            )
//            Text(task.name)
//            Text(task.responsible.joinToString(", "))
//            Text(task.deadline)
//        }
//    }
//}
//
//// Sample Task data class
//data class Task(
//    val name: String,
//    val responsible: List<String>,
//    val deadline: String,
//    val isCompleted: Boolean = false
//)
//
//// Sample preview
//@Preview(showBackground = true)
//@Composable
//fun ProjectDetailsScreenPreview() {
//    val sampleTasks = listOf(
//        Task("Task 1", listOf("John", "Mark"), "19.11 - 13 days"),
//        Task("Task 2", listOf("Alice"), "20.11 - 12 days")
//    )
//    ProjectDetailsScreen(
//        projectName = "Test",
//        onBackClick = {},
//        onInviteClick = {},
//        onNewTaskClick = {},
//        tasks = sampleTasks
//    )
//}