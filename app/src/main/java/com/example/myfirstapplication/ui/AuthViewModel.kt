package com.example.myfirstapplication.ui


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.myfirstapplication.data.AuthRepository

class AuthViewModel : ViewModel() {
    // Indica si el usuario está logueado
    var isLoggedIn by mutableStateOf(false)
        private set

    // Guarda un posible mensaje de error de login
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String) {
        if (AuthRepository.login(email, password)) {
            isLoggedIn = true
            errorMessage = null
        } else {
            errorMessage = "Invalid credentials"
        }
    }

    fun logout() {
        isLoggedIn = false
    }
}
