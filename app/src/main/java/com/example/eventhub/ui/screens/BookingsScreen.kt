package com.example.eventhub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BookingsScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("My bookings", style = MaterialTheme.typography.headlineMedium)
        Text("Sample bookings are shown below. Database storage will be added later.")
        listOf("Autumn Film Night" to "1 ticket · Confirmed", "Beginner Yoga" to "2 tickets · Confirmed").forEach { (title, detail) ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(title, style = MaterialTheme.typography.titleMedium)
                    Text(detail, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
