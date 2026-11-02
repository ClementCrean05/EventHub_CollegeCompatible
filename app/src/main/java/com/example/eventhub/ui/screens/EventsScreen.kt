/*
package com.example.eventhub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventhub.model.Event
import com.example.eventhub.ui.components.EventCard

@Composable
fun EventsScreen(events: List<Event>, onEventOpen: (Int) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { Text("Upcoming events", style = MaterialTheme.typography.headlineMedium) }
        items(events, key = { it.id }) { event -> EventCard(event) { onEventOpen(event.id) } }
    }
}
*/