package com.example.eventsapp.activites

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.R
import com.example.eventsapp.adpter.ConflictedEventAdapter
import com.example.eventsapp.adpter.EventAdapter
import com.example.eventsapp.models.Event
import com.example.eventsapp.utlity.ConflictHandler
import com.example.eventsapp.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var adapter: EventAdapter
    lateinit var mViewModel: MainViewModel
    lateinit var eventsListView: RecyclerView
    lateinit var conflictHandler: ConflictHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        eventsListView = findViewById(R.id.events_list)
        conflictHandler = ConflictHandler()
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mViewModel.read(this)
        observeViewModel()
    }

    private fun observeViewModel() {

        mViewModel.eventList.observe(this, Observer { events ->
            adapter = EventAdapter(this, events) { event, eventList, view, button, text ->
                val conflictAdapter =
                    ConflictedEventAdapter(
                        this,
                        conflictHandler.getOverlappedEvents(event, eventList) as ArrayList<Event>
                    )
                val layoutManager = LinearLayoutManager(this)
                view.layoutManager = layoutManager
                view.adapter = conflictAdapter
                button.visibility = View.GONE
                if (conflictHandler.isHaveConflictedEvents(event, eventList))
                    text.text = getString(R.string.conflict_text)
                else {
                    text.text = getString(R.string.no_conflict_events)
                    text.setTextColor(Color.GREEN)

                }
                text.visibility = View.VISIBLE
            }
            val layoutManger = LinearLayoutManager(this)
            eventsListView.layoutManager = layoutManger
            eventsListView.adapter = adapter
        })
    }
}
