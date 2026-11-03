package com.example.eventhub.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eventhub.model.Category
import com.example.eventhub.model.Event

@Database(
    entities = [Category::class, Event::class],
    version = 1,
    exportSchema = true
)
abstract class EventHubDatabase : RoomDatabase() {
    abstract fun eventHubDao(): EventHubDao

    companion object {
        @Volatile private var instance: EventHubDatabase? = null

        fun getInstance(context: Context): EventHubDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    EventHubDatabase::class.java,
                    "eventhub.db"
                ).build().also { instance = it }
            }
    }
}