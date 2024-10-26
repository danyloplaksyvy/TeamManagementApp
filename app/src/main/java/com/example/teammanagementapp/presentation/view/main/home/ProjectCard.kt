package com.example.teammanagementapp.presentation.view.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.teammanagementapp.R
import com.example.teammanagementapp.domain.model.Project

@Composable
fun ProjectCard(project: Project, onProjectClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, 8.dp),
        shape = RoundedCornerShape(45.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 1f
            )
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable { onProjectClick() }
                .padding(16.dp)
                .wrapContentSize()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.appbg),
                        "Icon",
                        modifier = Modifier.size(64.dp)
                    )
                    Text(
                        project.name,
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp),
                        modifier = Modifier
                            .widthIn(min = 125.dp, max = 125.dp)
                            .padding(horizontal = 8.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(25.dp)
                            )
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.CalendarMonth, "Calendar")
                        Column {
                            Text(
                                "26.10.2024",
                                modifier = Modifier.widthIn(max = 100.dp),
                                style = MaterialTheme.typography.labelSmall,
                            )
                            Text(
                                project.deadline,
                                modifier = Modifier.widthIn(max = 100.dp),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
//                        .background(
//                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f),
//                            shape = RoundedCornerShape(25.dp)
//                        )
                ) {
                    Text(
                        project.description,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
                        minLines = 1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Team: ${project.team.count()}")
//                    Image(painter = painterResource(R.drawable.peopleworking), "Team")
                    Text("32 days")
                }
            }
        }
    }
}