package com.example.eventsapp.repository

import android.content.Context
import android.util.Log
import com.example.eventsapp.models.Event
import org.json.JSONArray
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventRepository {
    private var json: String? = null
    private lateinit var inputStream: InputStream
    private var events: ArrayList<Event> = ArrayList()

    fun read(context: Context): ArrayList<Event> {
        inputStream = context.assets.open("mock.json")
        json = inputStream.bufferedReader().use { it.readText() }
        var eventsJson = JSONArray(json)
        return parseEvents(eventsJson)
    }

    private fun parseEvents(jsonArray: JSONArray): ArrayList<Event> {
        for (i in 0 until jsonArray.length()) {

            val eventJsonObject = jsonArray.getJSONObject(i)
            val title = eventJsonObject.getString("title")
            val startDateString: String = eventJsonObject.getString("start")
            val endDateString: String = eventJsonObject.getString("end")
            val startDate = convertStringToDate(startDateString)
            val endDate = convertStringToDate(endDateString)
            Log.d("ahmed", "title: $title ,,,,, string: $startDateString ,,,, Date: $startDate ")
            val event = Event(title, startDate, endDate, startDateString, endDateString)
            events.add(event)
        }
        events.sortBy { it.startDate }
        return events
    }

    private fun convertStringToDate(stringDate: String): Date? {
        return SimpleDateFormat("MMMM dd, yyyy hh:mm", Locale.ENGLISH).parse(stringDate)
    }
}
