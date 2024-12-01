package com.example.teammanagementapp.domain.model

data class Task(
    val id: String = "",
    val name: String = "",
    val participants: List<String> = emptyList(),
    val deadline: String = "",
    val isCompleted: Boolean = false
)