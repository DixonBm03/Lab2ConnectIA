package com.example.myfirstapplication.data

import com.example.myfirstapplication.data.models.User


object AuthRepository {
    // Usuario “quemado” para probar login
    private val testUser = User(email = "test", password = "1234")

    fun login(email: String, password: String): Boolean {
        // Verifica si coinciden con el usuario de prueba
        return email == testUser.email && password == testUser.password
    }
}
