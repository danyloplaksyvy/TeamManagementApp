package com.example.teammanagementapp.presentation.view.main.home.components.bottomsheet

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ManagingProjectAlertDialog(onDismissRequest: () -> Unit, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismissRequest()},
        title = { Text("Select Action") },
        text = { Text("What do you want to do with this project?") },
        confirmButton = {
            TextButton(onClick = {
                onEditClick()
            }) {
                Icon(Icons.Default.Edit, "Edit", tint = Color(1, 110, 9, 160))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDeleteClick()
            }) {
                Icon(Icons.Default.Delete, "Delete", tint = Color(148, 12, 12, 160))
            }
        }
    )
}