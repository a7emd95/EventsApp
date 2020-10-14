package com.example.eventsapp.models

import java.util.*

data class Event(
    val title: String,
    val startDate: Date?,
    val endDate: Date?,
    val start: String,
    val end: String
)