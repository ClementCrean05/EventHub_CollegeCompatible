package com.example.eventhub.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val eventColors = lightColorScheme(
    primary = EventPurple,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    primaryContainer = EventPurpleLight,
    secondary = EventTeal,
    background = EventBackground
)

@Composable
fun EventHubTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = eventColors, content = content)
}
