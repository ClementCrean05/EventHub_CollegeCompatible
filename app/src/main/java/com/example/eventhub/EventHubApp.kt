package com.example.eventhub

import androidx.compose.foundation.layout.*

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import androidx.navigation.NavType

import androidx.navigation.navArgument

import androidx.navigation.compose.*

import com.example.eventhub.data.sampleCategories

import com.example.eventhub.data.sampleEvents

import com.example.eventhub.ui.screens.*


@Composable
fun EventHubApp() {

    val navController = rememberNavController()
    Scaffold { padding ->

        NavHost(

            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)

        ) {

            composable("home") {

                HomeScreen(
                    events = sampleEvents, categories = sampleCategories,
                    onEventOpen = { id ->
                        navController.navigate("details/$id")

                    },
                    onExplore = { navController.navigate("events") }
                )

            }
            composable("events") {

                EventsScreen(
                    events = sampleEvents, categories = sampleCategories,

                    onEventOpen = { id ->
                        navController.navigate("details/$id")
                    }

                )

            }
            composable(

                route = "details/{eventId}",

                arguments = listOf(navArgument("eventId") {

                    type = NavType.IntType

                })

            ) { entry ->

                val id = entry.arguments?.getInt("eventId")

                val event = sampleEvents.firstOrNull { it.id == id }

                if (event == null) {

                    Column(Modifier.padding(16.dp)) {

                        Text("Event not found")

                        Button(onClick = { navController.popBackStack() }) {

                            Text("Back")

                        }

                    }

                } else {

                    val name = sampleCategories.firstOrNull {

                        it.id == event.categoryId

                    }?.name ?: "Uncategorised"

                    EventDetailsScreen(event = event, categoryName = name,

                        onBack = { navController.popBackStack() })

                }

            }

        }

    }

}