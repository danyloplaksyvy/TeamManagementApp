package com.example.teammanagementapp.presentation.view.main.home.components.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProjectBottomSheet(
    projectName: String,
    description: String,
    onProjectNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onParticipantsClick: () -> Unit,
    onCreateClick: (String, String) -> Unit
) {
    ModalBottomSheet(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        onDismissRequest = { onDismissRequest() },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            BasicTextField(
                value = projectName,
                onValueChange = onProjectNameChange,
                maxLines = 1,
                modifier = Modifier.padding(16.dp),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (projectName.isEmpty()) {
                            Text(
                                "Project's name...",
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerTextField()
                    }
                }
            )
            Text(
                "Project access",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onParticipantsClick() }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(Icons.Outlined.Group, "Team")
                Text(
                    "Choose team",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
                Icon(Icons.AutoMirrored.Outlined.KeyboardArrowRight, "ArrowRight")
            }
            BasicTextField(
                value = description,
                onValueChange = onDescriptionChange,
                minLines = 5,
                maxLines = 5,
                modifier = Modifier.padding(16.dp),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (description.isEmpty()) {
                            Text(
                                "Description...",
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerTextField()
                    }
                }
            )
            Button(
                enabled = projectName.isNotEmpty(),
                onClick = {
                    onCreateClick(projectName, description)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Create")
            }
        }
    }
}