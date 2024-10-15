package com.example.teammanagementapp.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teammanagementapp.R
import com.example.teammanagementapp.ui.theme.MyTheme

@Preview(showBackground = true)
@Composable
fun ProjectCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, 8.dp),
        shape = RoundedCornerShape(45.dp),
//        elevation = CardDefaults.cardElevation(draggedElevation = 5.dp),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier
                .clickable { }
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
                        "Expenses Mobile App",
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp),
                        modifier = Modifier
                            .widthIn(min = 125.dp, max = 125.dp)
                            .padding(horizontal = 8.dp)
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
                                "15 September",
                                modifier = Modifier.widthIn(max = 100.dp),
                                style = MaterialTheme.typography.labelSmall,
                            )
                            Text(
                                "31 October",
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
                        "Expense mobile app development provides more freedom to banking and other financial institutions",
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Team: 7 members")
//                    Image(painter = painterResource(R.drawable.peopleworking), "Team")
                    Text("32 days")
                }
            }
        }
    }
}