package com.example.myfirstapplication.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Job(
    val id: Int,
    val title: String,
    val location: String,
    val description: String,
    val requirements: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsScreen(onJobClick: (Int) -> Unit) {
    val jobs = listOf(
        Job(1, "Software Developer at Microsoft", "Redmond, WA", "Description...", "Requirements..."),
        Job(2, "Android Developer at Google", "Mountain View, CA", "Description...", "Requirements...")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Jobs") }
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(jobs) { job ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onJobClick(job.id) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(job.title, style = MaterialTheme.typography.titleMedium)
                        Text(job.location, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
