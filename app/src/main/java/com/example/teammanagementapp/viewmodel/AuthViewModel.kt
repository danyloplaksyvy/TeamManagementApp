package com.example.teammanagementapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _signInResult = Channel<Boolean>()
    val signInResult = _signInResult.receiveAsFlow()
    private val _signUpResult = Channel<Boolean>()
    val signUpResult = _signUpResult.receiveAsFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun signUpUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                _signUpResult.send(true)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = when (e) {
                    is FirebaseAuthWeakPasswordException -> "Password is weak. Minimum is 6 chars."
                    else -> "Authentication failed: ${e.localizedMessage}"
                }
                _signUpResult.send(false)

            }
        }
    }

    fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                _signInResult.send(true)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = when (e) {
                    is FirebaseAuthEmailException -> "The email address is badly formatted."
                    is FirebaseAuthInvalidCredentialsException -> "The supplied auth credential is incorrect."
                    else -> "Authentication failed: ${e.localizedMessage}"
                }
                _signInResult.send(false)
            }
        }
    }

}