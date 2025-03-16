package com.example.myfirstapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapp.ui.screens.LoginScreen
import com.example.myfirstapplication.ui.AuthViewModel
import com.example.myfirstapplication.ui.screens.ChatScreen
import com.example.myfirstapplication.ui.screens.HomeScreen
import com.example.myfirstapplication.ui.screens.JobDetailsScreen
import com.example.myfirstapplication.ui.screens.JobsScreen
import com.example.myfirstapplication.ui.screens.NotificationsScreen
import com.example.myfirstapplication.ui.screens.ProfileScreen
import com.example.myfirstapplication.ui.screens.RegisterScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel()
) {
    // Declaramos el NavHost
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        // Pantalla de Login
        composable("login") {
            // Usamos LaunchedEffect para navegar a "home" solo cuando isLoggedIn cambie a true
            LaunchedEffect(authViewModel.isLoggedIn) {
                if (authViewModel.isLoggedIn) {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }

            // Si no está logueado, mostramos la pantalla de Login
            if (!authViewModel.isLoggedIn) {
                LoginScreen(
                    authViewModel = authViewModel,
                    onLoginSuccess = {
                        navController.navigate("home") {
                            // Eliminar pantalla de Login del back stack
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate("register")
                    }
                )
            }
        }

        // Pantalla de Registro
        composable("register") {
            RegisterScreen(
                onRegisterDone = {
                    // Tras registro, volvemos a login
                    navController.popBackStack()
                }
            )
        }

        // Pantalla de Home
        composable("home") {
            HomeScreen(
                onNavigateToProfile = { navController.navigate("profile") },
                onNavigateToJobs = { navController.navigate("jobs") },
                onNavigateToChat = { navController.navigate("chat") },
                onNavigateToNotifications = { navController.navigate("notifications") }
            )
        }

        // Pantalla de Perfil
        composable("profile") {
            ProfileScreen()
        }

        // Pantalla de Jobs
        composable("jobs") {
            JobsScreen(onJobClick = { jobId ->
                navController.navigate("details/$jobId")
            })
        }

        // Pantalla de Detalle de un Job
        composable("details/{jobId}") { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")?.toIntOrNull()
            JobDetailsScreen(jobId)
        }

        // Pantalla de Chat
        composable("chat") {
            ChatScreen()
        }

        // Pantalla de Notificaciones
        composable("notifications") {
            NotificationsScreen()
        }

        // Agrega más pantallas aquí (Resume, Interview, etc.) si las necesitas
    }
}
