package com.example.eventsapp.utlity

import com.example.eventsapp.models.Event
import java.sql.Timestamp

class ConflictHandler {
    private fun doOverlap(sourceEvent: Event, targetEvent: Event): Boolean {
        if (Timestamp(sourceEvent.startDate!!.time) < Timestamp(targetEvent.endDate!!.time) && Timestamp(
                targetEvent.startDate!!.time
            ) < Timestamp(sourceEvent.endDate!!.time)
        )
            return true
        return false
    }

    fun getOverlappedEvents(event: Event, eventsList: List<Event>): List<Event> {
        var overlappedEvents: ArrayList<Event> = ArrayList()
        for (i in 0 until eventsList.size) {
            if (event == eventsList.get(i))
                continue
            if (doOverlap(event, eventsList.get(i)))
                overlappedEvents.add(eventsList.get(i))
        }
        return overlappedEvents
    }
    fun isHaveConflictedEvents(event: Event , eventsList: List<Event>) : Boolean{
        return getOverlappedEvents(event, eventsList).isNotEmpty()
    }
}