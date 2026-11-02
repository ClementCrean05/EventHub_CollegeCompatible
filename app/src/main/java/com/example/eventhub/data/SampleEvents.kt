package com.example.eventhub.data


import com.example.eventhub.model.Category

import com.example.eventhub.model.Event


val sampleCategories = listOf(

    Category(1, "Entertainment"),

    Category(2, "Wellbeing"),

    Category(3, "Careers"),

    Category(4, "Community"),

    Category(5, "Creative")

)


val sampleEvents = listOf(

    Event(

        id = 10, title = "Autumn Film Night", categoryId = 1,

        date = "24 Oct - 19:00", location = "Arts Hall",

        description = "Short films followed by discussion.",

        priceEuro = 6, availablePlaces = 40

    ),

    Event(

        id = 20, title = "Beginner Yoga", categoryId = 2,

        date = "27 Oct - 17:30", location = "Studio B",

        description = "A relaxed session for first time participants.",

        priceEuro = 4, availablePlaces = 18

    ),

    Event(

        id = 30, title = "Tech Careers Talk", categoryId = 3,

        date = "30 Oct - 13:00", location = "Innovation Centre",

        description = "Meet graduates working in software development.",

        priceEuro = 0, availablePlaces = 65

    ),

    Event(

        id = 40, title = "Weekend Food Market", categoryId = 4,

        date = "2 Nov - 11:00", location = "Central Square",

        description = "Local food stalls and live music.",

        priceEuro = 0, availablePlaces = 120

    ),

    Event(

        id = 50, title = "Photography Walk", categoryId = 5,

        date = "6 Nov - 15:00", location = "Main Gate",

        description = "Practise photography on a guided walk.",

        priceEuro = 3, availablePlaces = 24

    )

)
