package com.example.eventhub.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.eventhub.model.Category
import com.example.eventhub.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventHubDao {
    @Query("SELECT * FROM categories ORDER BY id")
    fun observeCategories(): Flow<List<Category>>

    @Query("SELECT * FROM events ORDER BY id")
    fun observeEvents(): Flow<List<Event>>

    @Query("SELECT COUNT(*) FROM categories")
    suspend fun categoryCount(): Int

    @Query("SELECT COUNT(*) FROM events")
    suspend fun eventCount(): Int

    @Insert
    suspend fun insertCategories(categories: List<Category>)

    @Insert
    suspend fun insertEvents(events: List<Event>)

    @Insert
    suspend fun insertEvent(event: Event): Long
}