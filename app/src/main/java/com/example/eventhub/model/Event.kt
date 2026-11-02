package com.example.eventhub.model


data class Event(

    val id: Int,
    val title: String,
    val categoryId: Int,
    val date: String,
    val location: String,
    val description: String,
    val priceEuro: Int,
    val availablePlaces: Int

)


data class Booking(val eventId: Int, val tickets: Int)
