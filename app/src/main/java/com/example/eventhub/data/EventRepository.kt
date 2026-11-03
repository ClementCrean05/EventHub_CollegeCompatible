package com.example.eventhub.data

import androidx.room.withTransaction
import com.example.eventhub.data.local.EventHubDatabase
import com.example.eventhub.model.Event

class EventRepository(private val database: EventHubDatabase) {
    private val dao = database.eventHubDao()
    val events = dao.observeEvents()
    val categories = dao.observeCategories()

    suspend fun seedIfEmpty() {
        database.withTransaction {
            if (dao.categoryCount() == 0 && dao.eventCount() == 0) {
                dao.insertCategories(sampleCategories)
                dao.insertEvents(sampleEvents)
            }
        }
    }

    suspend fun addEvent(categoryId: Int): Long = dao.insertEvent(
        Event(
            id = 0,
            title = "Student Study Meetup",
            categoryId = categoryId,
            date = "To be arranged",
            location = "Campus library",
            description = "A new event saved during the Room practical.",
            priceEuro = 0,
            availablePlaces = 12
        )
    )
}