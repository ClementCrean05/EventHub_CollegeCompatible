package com.example.eventhub

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.*
import com.example.eventhub.ui.EventHubViewModel
import com.example.eventhub.ui.screens.*

@Composable
fun EventHubApp() {
    val application = LocalContext.current.applicationContext as Application
    val model: EventHubViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    )
    val state by model.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    Scaffold { padding ->
        if (state.isLoading) {
            Column(Modifier.padding(padding).padding(16.dp)) {
                CircularProgressIndicator()
                Text("Loading saved events…")
            }
        } else if (state.loadError != null) {
            Text(
                text = "Database error: ${state.loadError}",
                modifier = Modifier.padding(padding).padding(16.dp)
            )
        } else {
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(padding)
            ) {
                composable("home") {
                    HomeScreen(
                        events = state.events,
                        categories = state.categories,
                        onEventOpen = { id -> navController.navigate("details/$id") },
                        onExplore = { navController.navigate("events") }
                    )
                }
                composable("events") {
                    Column(Modifier.fillMaxSize()) {
                        Button(
                            onClick = model::addTestEvent,
                            enabled = !state.isAdding && state.categories.isNotEmpty(),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Text(if (state.isAdding) "Saving…" else "Add test event")
                        }
                        state.actionMessage?.let { message ->
                            Text(message, Modifier.padding(horizontal = 16.dp))
                        }
                        Box(Modifier.weight(1f)) {
                            EventsScreen(
                                events = state.events,
                                categories = state.categories,
                                onEventOpen = { id ->
                                    navController.navigate("details/$id")
                                }
                            )
                        }
                    }
                }
                composable(
                    route = "details/{eventId}",
                    arguments = listOf(navArgument("eventId") {
                        type = NavType.IntType
                    })
                ) { entry ->
                    val id = entry.arguments?.getInt("eventId")
                    val event = state.events.firstOrNull { it.id == id }
                    if (event == null) {
                        Column(Modifier.padding(16.dp)) {
                            Text("Event not found")
                            Button(onClick = { navController.popBackStack() }) {
                                Text("Back")
                            }
                        }
                    } else {
                        val categoryName = state.categories.firstOrNull {
                            it.id == event.categoryId
                        }?.name ?: "Uncategorised"
                        EventDetailsScreen(
                            event = event,
                            categoryName = categoryName,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
} 