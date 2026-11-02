package com.example.eventhub.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.eventhub.model.Category
import com.example.eventhub.model.Event
import com.example.eventhub.ui.components.EventCard


@Composable

fun HomeScreen(
    events: List<Event>, categories: List<Category>,
    onEventOpen: (Int) -> Unit, onExplore: () -> Unit
) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        item {
            Text("Discover something new",
                style = MaterialTheme.typography.headlineMedium)
            Button(onClick = onExplore) { Text("Browse all events") }
            Text("Featured events")
        }

        items(events.take(2), key = { it.id }) { event ->

            val name = categories.firstOrNull {
                it.id == event.categoryId
            }?.name ?: "Uncategorised"

            EventCard(event = event, categoryName = name,
                onOpen = { onEventOpen(event.id) })
        }

    }

}

@Composable

fun EventsScreen(

    events: List<Event>, categories: List<Category>,

    onEventOpen: (Int) -> Unit

) {

    LazyColumn(

        contentPadding = PaddingValues(16.dp),

        verticalArrangement = Arrangement.spacedBy(14.dp)

    ) {

        item { Text("All events",

            style = MaterialTheme.typography.headlineMedium) }

        items(events, key = { it.id }) { event ->

            val name = categories.firstOrNull {

                it.id == event.categoryId

            }?.name ?: "Uncategorised"

            EventCard(event = event, categoryName = name,

                onOpen = { onEventOpen(event.id) })

        }

    }

}