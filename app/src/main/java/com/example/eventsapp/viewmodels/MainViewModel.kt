package com.example.eventsapp.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventsapp.models.Event
import com.example.eventsapp.repository.EventRepository

class MainViewModel : ViewModel() {
    private val mutableEventsList: MutableLiveData<List<Event>> = MutableLiveData()
    val eventList: LiveData<List<Event>> = mutableEventsList
    private var eventRepository: EventRepository = EventRepository()

    fun read(context: Context) {
        mutableEventsList.postValue(eventRepository.read(context))
    }
}