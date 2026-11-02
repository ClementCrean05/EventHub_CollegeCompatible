package com.example.eventhub.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventhub.model.Event


@Composable

fun EventCard(

    event: Event,
    categoryName: String,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier

) {

    Card(

        onClick = onOpen,
        modifier = modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant

        )

    ) {

        Column(

            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)

        ) {

            Text(

                categoryName.uppercase(),
                style = MaterialTheme.typography.labelMedium

            )

            Text(event.title, style = MaterialTheme.typography.titleLarge)
            Text("${event.date} | ${event.location}")
            Text(

                if (event.priceEuro == 0) "Free"
                else "EUR ${event.priceEuro} per ticket"

            )

        }

    }

}

/*
package com.example.eventhub.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventhub.model.Event

@Composable
fun EventCard(event: Event, onOpen: () -> Unit) {
    Card(onClick = onOpen, modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(event.category.uppercase(), style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary)
            Text(event.title, style = MaterialTheme.typography.titleLarge)
            Text("${event.date}  •  ${event.location}", style = MaterialTheme.typography.bodyMedium)
            Text(if (event.priceEuro == 0) "Free" else "€${event.priceEuro} per ticket",
                style = MaterialTheme.typography.labelLarge)
        }
    }
}
*/