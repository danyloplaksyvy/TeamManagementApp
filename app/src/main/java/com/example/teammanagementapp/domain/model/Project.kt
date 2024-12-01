package com.example.teammanagementapp.domain.model


data class Project(
    val id: String = "", // Unique identifier for the project
    val name: String = "",
    val description: String? = null,
    val team: List<String> = listOf(),
    val projectPicture: String = "",
    val deadline: String = "31.12.2024",
    val tasks: List<Task> = listOf()
)