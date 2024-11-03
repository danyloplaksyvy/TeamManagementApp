package com.example.teammanagementapp.domain.model

data class Task(
    val name: String,
    val responsible: List<String>,
    val deadline: String,
    val isCompleted: Boolean = false
)