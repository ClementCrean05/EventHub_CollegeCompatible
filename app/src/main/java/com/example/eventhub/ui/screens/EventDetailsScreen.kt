package com.example.eventhub.ui.screens

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import com.example.eventhub.model.Event


@Composable

fun EventDetailsScreen(

    event: Event,
    categoryName: String,
    onBack: () -> Unit

) {

    Column(

        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        OutlinedButton(onClick = onBack) { Text("Back") }
        Text(categoryName, style = MaterialTheme.typography.labelLarge)
        Text(event.title, style = MaterialTheme.typography.headlineMedium)
        Card(modifier = Modifier.fillMaxWidth()) {

            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)

            ) {

                Text("Date: ${event.date}")
                Text("Location: ${event.location}")
                Text("Places: ${event.availablePlaces}")

                Text(
                    if (event.priceEuro == 0) "Free admission"
                    else "EUR ${event.priceEuro} per ticket"
                )
            }
        }

        Text("About this event", style = MaterialTheme.typography.titleLarge)
        Text(event.description)

    }
}
/*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventhub.model.Event

@Composable
fun EventDetailsScreen(event: Event, onBack: () -> Unit, onViewBookings: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        TextButton(onClick = onBack) { Text("← Back to events") }
        Text(event.category, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Text(event.title, style = MaterialTheme.typography.headlineMedium)
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
            Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text("When: ${event.date}")
                    Text("Where: ${event.location}")
                }
                Text("Places available: ${event.availablePlaces}")
                Text(if (event.priceEuro == 0) "Admission: Free" else "Admission: €${event.priceEuro}")
            }
        }
        Text(event.description, style = MaterialTheme.typography.bodyLarge)
        Text("Booking features will be added in future lessons.", style = MaterialTheme.typography.bodySmall)
        OutlinedButton(onClick = onViewBookings) { Text("View sample bookings") }

        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
            Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Ticket Selection", style = MaterialTheme.typography.headlineMedium)
                Text("Tickets are €${event.priceEuro} with ${event.availablePlaces} places still available!!!")
            }

            var quantity by rememberSaveable { mutableIntStateOf(1) }
            Text("Quantity: $quantity", fontSize = 22.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                if (quantity >=0 && quantity <=5) {
                    Button(onClick = { quantity++ }) { Text("Increase quantity") }
                    Button(onClick = { quantity-- }) { Text("Decrease quantity") }
                }

            }

            if (quantity == 0) {
                Text("No tickets selected", fontSize = 22.sp)
            }
            else if (quantity == 4 || quantity == 5) {
                Text("Group selection", fontSize = 22.sp)
            }
            else Text("Standard Selection", fontSize = 22.sp)
        }




    }
}
*/