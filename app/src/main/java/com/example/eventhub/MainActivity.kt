package com.example.eventhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.eventhub.data.sampleEvents
import com.example.eventhub.ui.screens.*
import com.example.eventhub.ui.theme.EventHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { EventHubTheme { EventHubApp() } }
    }
}
/*
private enum class AppScreen { HOME, EVENTS, DETAILS, BOOKINGS, PROFILE }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventHubApp() {
    var screenName by rememberSaveable { mutableStateOf(AppScreen.HOME.name) }
    var selectedEventId by rememberSaveable { mutableIntStateOf(sampleEvents.first().id) }
    val screen = AppScreen.valueOf(screenName)
    val selectedEvent = sampleEvents.firstOrNull { it.id == selectedEventId } ?: sampleEvents.first()
    fun openEvent(id: Int) { selectedEventId = id; screenName = AppScreen.DETAILS.name }

    Scaffold(
        topBar = { TopAppBar(title = { Text("EventHub") }) },
        bottomBar = {
            NavigationBar {
                listOf(
                    Triple(AppScreen.HOME, "Home", "H"),
                    Triple(AppScreen.EVENTS, "Events", "E"),
                    Triple(AppScreen.BOOKINGS, "Bookings", "B"),
                    Triple(AppScreen.PROFILE, "Profile", "P")
                ).forEach { (destination, label, icon) ->
                    NavigationBarItem(
                        selected = screen == destination,
                        onClick = { screenName = destination.name },
                        icon = { Text(icon) },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            when (screen) {
                AppScreen.HOME -> HomeScreen(sampleEvents, ::openEvent) { screenName = AppScreen.EVENTS.name }
                AppScreen.EVENTS -> EventsScreen(sampleEvents, ::openEvent)
                AppScreen.DETAILS -> EventDetailsScreen(selectedEvent,
                    onBack = { screenName = AppScreen.EVENTS.name },
                    onViewBookings = { screenName = AppScreen.BOOKINGS.name })
                AppScreen.BOOKINGS -> BookingsScreen()
                AppScreen.PROFILE -> ProfileScreen()
            }
        }
    }
}
*/