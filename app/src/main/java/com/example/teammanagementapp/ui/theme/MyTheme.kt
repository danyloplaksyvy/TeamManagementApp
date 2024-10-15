package com.example.teammanagementapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyTheme(content: @Composable () -> Unit) {
    val colors = lightColorScheme(
        primary = Color(101, 163, 75, 255),
        onPrimary = Color(237, 244, 242, 255),
        secondary = Color(145, 201, 232, 255),
        onSecondary = Color(237, 244, 242, 255),
        tertiary = Color(193, 160, 107, 255),
        onTertiary = Color(237, 244, 242, 255),
//        surface = Color(63, 99, 203, 255),
//        onSurface = Color(5, 9, 21),
        background = Color(248, 249, 248, 255),
        onBackground = Color(33, 31, 30, 255),
        outline = Color(101, 163, 75, 255),
    )
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}