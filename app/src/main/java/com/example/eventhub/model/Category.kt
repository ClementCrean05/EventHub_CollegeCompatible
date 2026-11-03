package com.example.eventhub.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "categories")
data class Category(
    @PrimaryKey val id: Int,
    val name: String
)