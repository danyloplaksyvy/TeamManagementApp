package com.example.teammanagementapp.domain.model

import android.media.Image

data class Project(
    val id: String = "", // Unique identifier for the project
    val name: String = "",
    val description: String = "There is no description",
    val team: List<String> = listOf(),
    val projectPicture: String = "", // Use String if storing URI or path
    val deadline: String = "31.12.2024"
)