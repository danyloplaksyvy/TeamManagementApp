package com.example.teammanagementapp.presentation.view.main.project.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.teammanagementapp.domain.model.Task
import com.example.teammanagementapp.utils.TableDataCheckIcon
import com.example.teammanagementapp.utils.TableDataText

@Composable
fun TableDataRow(task: Task) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TableDataCheckIcon(status = false, modifier = Modifier.weight(0.5f))
        TableDataText(task.name, modifier = Modifier.weight(1f))
        TableDataText(task.participants.toString(), modifier = Modifier.weight(1f))
        TableDataText(task.deadline, modifier = Modifier.weight(1f))
    }
}