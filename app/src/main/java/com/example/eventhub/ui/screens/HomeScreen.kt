/*
package com.example.eventhub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventhub.model.Event
import com.example.eventhub.ui.components.EventCard

@Composable
fun HomeScreen(events: List<Event>, onEventOpen: (Int) -> Unit, onExplore: () -> Unit) {
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Text("Discover something new", style = MaterialTheme.typography.headlineMedium)
            Text("Activities, talks and experiences in one place.")
        }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("This week", style = MaterialTheme.typography.titleLarge)
                    Text("Explore events and save your place.")
                    Button(onClick = onExplore) { Text("Browse all events") }
                }
            }
        }
        item { Text("Featured events", style = MaterialTheme.typography.titleLarge) }
        items(events.take(2).size) { index ->
            val event = events[index]
            EventCard(event) { onEventOpen(event.id) }
        }
    }
}
*/