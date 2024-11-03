package com.example.teammanagementapp.presentation.view.main.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.teammanagementapp.domain.model.Task

@Composable
fun ProjectTaskTable() {
    // Header Row
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Name", fontWeight = FontWeight.Bold)
        Text("Responsible", fontWeight = FontWeight.Bold)
        Text("Deadline", fontWeight = FontWeight.Bold)
    }

    // Task Rows
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
}
