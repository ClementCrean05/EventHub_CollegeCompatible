package com.example.eventhub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.eventhub.model.Category
import com.example.eventhub.model.Event
import com.example.eventhub.ui.components.EventCard

@Composable
fun ManageEventsScreen(
    events: List<Event>,
    categories: List<Category>,
    isSaving: Boolean,
    actionMessage: String?,
    onAddEvent: (Event) -> Unit,
    onDeleteEvent: (Int) -> Unit,
    onEventOpen: (Int) -> Unit,
    Back: () -> Unit
) {
    var showAddForm by rememberSaveable { mutableStateOf(false) }
    var selectedCategoryId by rememberSaveable { mutableStateOf<Int?>(null) }
    var filterMenuOpen by remember { mutableStateOf(false) }
    var eventToDelete by remember { mutableStateOf<Event?>(null) }
    val filterLabel = categories.firstOrNull {
        it.id == selectedCategoryId
    }?.name ?: "All categories"
    val visibleEvents = if (selectedCategoryId == null) events else
        events.filter { it.categoryId == selectedCategoryId }


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {


        item { Text("Manage events", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = Back) {
                Text("Back")
            }
           }

        item {
            Button(onClick = { showAddForm = true },
                enabled = !isSaving && categories.isNotEmpty()) {
                Text("Add event")
            }
        }
        item {
            Box {
                OutlinedButton(onClick = { filterMenuOpen = true }) {

                    Text("Category: $filterLabel")
                }
                DropdownMenu(expanded = filterMenuOpen,
                    onDismissRequest = { filterMenuOpen = false }) {
                    DropdownMenuItem(text = { Text("All categories") }, onClick = {
                        selectedCategoryId = null
                        filterMenuOpen = false
                    })
                    categories.forEach { category ->
                        DropdownMenuItem(text = { Text(category.name) }, onClick = {
                            selectedCategoryId = category.id
                            filterMenuOpen = false
                        })
                    }
                }
            }
        }
        if (isSaving) { item { Text("Saving changes…") } }
        actionMessage?.let { message -> item { Text(message) } }
        if (visibleEvents.isEmpty()) { item { Text("No events in this category.") } }
        items(visibleEvents, key = { it.id }) { event ->
            val name = categories.firstOrNull {
                it.id == event.categoryId
            }?.name ?: "Uncategorised"
            Column {
                EventCard(event = event, categoryName = name,
                    onOpen = { onEventOpen(event.id) })
                TextButton(enabled = !isSaving,
                    onClick = { eventToDelete = event }) { Text("Delete") }
            }
        }
    }
    if (showAddForm) {
        AddEventDialog(categories = categories,
            onDismiss = { showAddForm = false },
            onSave = { event ->
                onAddEvent(event)
                showAddForm = false
            })
    }
    eventToDelete?.let { event ->
        AlertDialog(onDismissRequest = { eventToDelete = null },
            title = { Text("Delete event?") },
            text = { Text("Are you sure you want to delete ${event.title}?") },
            confirmButton = {
                TextButton(enabled = !isSaving, onClick = {
                    onDeleteEvent(event.id)
                    eventToDelete = null
                }) { Text("Delete") }
            },
            dismissButton = {
                TextButton(onClick = { eventToDelete = null }) { Text("Cancel") }
            })
    }
}

@Composable
private fun AddEventDialog(
    categories: List<Category>,
    onDismiss: () -> Unit,
    onSave: (Event) -> Unit
) {

    var title by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("0") }
    var places by rememberSaveable { mutableStateOf("12") }
    var categoryId by rememberSaveable { mutableStateOf(categories.firstOrNull()?.id) }
    var categoryMenuOpen by remember { mutableStateOf(false) }
    val selectedCategory = categories.firstOrNull { it.id == categoryId }
    val priceValue = price.toIntOrNull()
    val placesValue = places.toIntOrNull()
    val canSave = title.isNotBlank() && date.isNotBlank() &&
            location.isNotBlank() && description.isNotBlank() &&
            selectedCategory != null && priceValue != null && priceValue >= 0 &&

    placesValue != null && placesValue >= 0

    AlertDialog(onDismissRequest = onDismiss,
        title = { Text("Add event") },
        text = {
            Column(Modifier.fillMaxWidth().heightIn(max = 400.dp)
                .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Enter the details and choose a category.")
                OutlinedTextField(value = title, onValueChange = { title = it },
                    label = { Text("Event title") }, singleLine = true,
                    modifier = Modifier.fillMaxWidth())
                Box {
                    OutlinedButton(onClick = { categoryMenuOpen = true }) {
                        Text(selectedCategory?.name ?: "Choose category")
                    }
                    DropdownMenu(expanded = categoryMenuOpen,
                        onDismissRequest = { categoryMenuOpen = false }) {
                        categories.forEach { category ->
                            DropdownMenuItem(text = { Text(category.name) }, onClick = {
                                categoryId = category.id
                                categoryMenuOpen = false
                            })
                        }
                    }
                }
                OutlinedTextField(value = date, onValueChange = { date = it },
                    label = { Text("Date and time") }, singleLine = true,
                    modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = location, onValueChange = { location = it },
                    label = { Text("Location") }, singleLine = true,
                    modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = description, onValueChange = { description = it },
                    label = { Text("Description") }, minLines = 2,
                    modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = price, onValueChange = { price = it },
                    label = { Text("Price in whole euros") },
                    supportingText = { Text("Enter 0 for free admission.") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = places, onValueChange = { places = it },
                    label = { Text("Available places") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true, modifier = Modifier.fillMaxWidth())
            }
        },
        confirmButton = {
            TextButton(enabled = canSave, onClick = {
                val validCategoryId = selectedCategory?.id ?: return@TextButton
                val validPrice = price.toIntOrNull() ?: return@TextButton
                val validPlaces = places.toIntOrNull() ?: return@TextButton
                onSave(Event(id = 0, title = title.trim(), categoryId = validCategoryId,
                    date = date.trim(), location = location.trim(),
                    description = description.trim(), priceEuro = validPrice,
                    availablePlaces = validPlaces))
            }) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } })


}