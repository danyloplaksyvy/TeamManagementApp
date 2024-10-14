package com.example.teammanagementapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyTheme(content: @Composable () -> Unit) {
    val colors = lightColorScheme(
        primary = Color(49, 71, 58, 255),
        onPrimary = Color(237, 244, 242, 255),
        secondary = Color(49, 71, 58, 255),
        onSecondary = Color(237, 244, 242, 255),
        tertiary = Color(132, 43, 13, 255),
        onTertiary = Color(237, 244, 242, 255),
//        surface = Color(63, 99, 203, 255),
//        onSurface = Color(5, 9, 21),
        background = Color(245, 248, 246, 255),
        onBackground = Color(37, 44, 37, 255),
        outline = Color(49, 71, 58, 255),
    )
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}