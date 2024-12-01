package com.example.teammanagementapp.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max

@Composable
fun NotImplementedAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(Icons.Default.WarningAmber, contentDescription = "Warning")
        },
        title = {
            Text(text = "Oops!")
        },
        text = {
            Text(text = "This part of program isn't implement yet.")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
fun TableTitleText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { })
}

@Composable
fun TableDataText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text, modifier = modifier.padding(6.dp), textAlign = TextAlign.Center, maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun TableDataCheckIcon(modifier: Modifier = Modifier, status: Boolean) {
    Icon(imageVector = if (!status) Icons.Outlined.CheckCircle else Icons.Filled.CheckCircle,
        "Check",
        tint = MaterialTheme.colorScheme.secondary,
        modifier = modifier
            .padding(8.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .clickable { })
}