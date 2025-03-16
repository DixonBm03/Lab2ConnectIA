package com.example.myfirstapplication.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailsScreen(jobId: Int?) {
    // En un proyecto real, aquí obtendrías el job de un ViewModel o repositorio
    val job = remember {
        // Dummy data
        Job(
            id = jobId ?: 0,
            title = "Software Developer at Microsoft",
            location = "Redmond, WA",
            description = "Desc...",
            requirements = "Req..."
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Details") }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
        ) {
            Text(job.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Location: ${job.location}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Description: ${job.description}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Requirements: ${job.requirements}")
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* Solicitar / Aplicar */ }) {
                Text("Apply")
            }
        }
    }
}
