package com.example.teammanagementapp.domain.model

import android.media.Image

data class Project(
    val name: String = "",
    val description: String = "",
    val team: List<String> = listOf(),
    val projectPicture: String = "", // Use String if storing URI or path
    val deadline: String = ""
)