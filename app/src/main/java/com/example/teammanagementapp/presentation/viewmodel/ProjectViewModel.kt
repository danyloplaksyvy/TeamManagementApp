package com.example.teammanagementapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teammanagementapp.domain.model.Project
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProjectViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects

    // Fetch projects from Firestore and update _projects state
    init {
        fetchProjects()
    }

    fun fetchProjects() {
        db.collection("projects")
            .get()
            .addOnSuccessListener { documents ->
                val projectList = documents.mapNotNull { document ->
                    document.toObject(Project::class.java)
                }
                _projects.value = projectList
            }
            .addOnFailureListener {
                Log.e("ProjectViewModel", "Error getting document")
            }
    }

    fun addProject(name: String, description: String) {
        val newProject = Project(
            name = name,
            description = description,
            team = listOf("John", "Ann", "Ron"),  // Need fix
            deadline = "31.12.2024", // Need fix
            projectPicture = "" // Need fix
        )

        db.collection("projects").add(newProject)
            .addOnSuccessListener { fetchProjects() }  // Refresh after adding project
            .addOnFailureListener { e -> Log.e("ProjectViewModel", "Error adding document", e) }
    }
}