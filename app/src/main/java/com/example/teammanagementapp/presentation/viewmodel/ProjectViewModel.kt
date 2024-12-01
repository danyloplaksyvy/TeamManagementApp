package com.example.teammanagementapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teammanagementapp.domain.model.Project
import com.example.teammanagementapp.domain.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ProjectViewModel : ViewModel() {
    private val db = Firebase.firestore

    private val _project = MutableLiveData<Project?>()
    val project: MutableLiveData<Project?> get() = _project

    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    // Fetch projects from Firestore and update _projects state
    init {
        fetchProjects()
    }

    fun fetchProjects() {
        db.collection("projects")
            .get()
            .addOnSuccessListener { documents ->
                val projectList = documents.mapNotNull { document ->
                    document.toObject(Project::class.java).copy(id = document.id)
                }
                _projects.value = projectList
            }
            .addOnFailureListener {
                Log.e("ProjectViewModel", "Error getting documents", it)
            }
    }

    fun addProject(name: String, description: String) {
        val newProject = Project(
//            id = "", // The ID will be assigned by Firestore when added
            name = name,
            description = description,
            team = listOf("John", "Ann", "Ron"), // Example team members; adjust as needed
            deadline = "31.12.2024", // Example deadline; adjust as needed
            projectPicture = "", // Example placeholder for project picture
        )

        db.collection("projects").add(newProject)
            .addOnSuccessListener { fetchProjects() } // Refresh the list after adding a project
            .addOnFailureListener { e -> Log.e("ProjectViewModel", "Error adding document", e) }
    }

    fun updateProject(projectId: String, name: String, description: String) {
        val updates = mapOf(
            "name" to name,
            "description" to description
        )
        db.collection("projects").document(projectId)
            .update(updates)
            .addOnSuccessListener { fetchProjects() }
    }

    fun deleteProject(projectId: String) {
        db.collection("projects").document(projectId)
            .delete()
            .addOnSuccessListener { fetchProjects() }
    }

    fun loadProject(projectId: String) {
        // Fetch project data from Firestore using the projectId
        db.collection("projects")
            .document(projectId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val project = document.toObject(Project::class.java)?.copy(id = document.id)
                    _project.value = project
                }
            }
            .addOnFailureListener { exception ->
                Log.e("ProjectViewModel", "Error fetching project", exception)
            }
    }

    fun loadTasks(projectId: String) {
        db.collection("projects")
            .document(projectId)
            .collection("tasks")
            .get()
            .addOnSuccessListener { documents ->
                val taskList = documents.mapNotNull { document ->
                    document.toObject(Task::class.java).copy(id = document.id)
                }
                _tasks.value = taskList
            }
            .addOnFailureListener { exception ->
                Log.e("ProjectViewModel", "Error loading tasks", exception)
            }
    }

    fun addTask(projectId: String, name: String, participants: List<String>, deadline: String) {
        val newTask = Task(name = name, participants = participants, deadline = deadline)

        db.collection("projects")
            .document(projectId)
            .collection("tasks")
            .add(newTask)
            .addOnSuccessListener { loadTasks(projectId) }
            .addOnFailureListener { exception ->
                Log.e("ProjectViewModel", "Error adding task", exception)
            }
    }
}