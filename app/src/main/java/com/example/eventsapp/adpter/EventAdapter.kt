package com.example.eventsapp.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.R
import com.example.eventsapp.models.Event

class EventAdapter(
    private val context: Context,
    private val eventsList: List<Event>,
    val onClicked: (Event, List<Event>, RecyclerView, Button, TextView) -> Unit
) :
    RecyclerView.Adapter<EventAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.dataBinding(eventsList.get(position), eventsList, onClicked)

    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var titleVieww = itemView.findViewById<TextView>(R.id.event_title_conflict)
        private var startDateView = itemView.findViewById<TextView>(R.id.start_date)
        private var endDateView = itemView.findViewById<TextView>(R.id.end_date)
        private var conflictBtn = itemView.findViewById<Button>(R.id.conflict_btn)
        private var conflictedEventView = itemView.findViewById<RecyclerView>(R.id.cofliect_events)
        private var confictedEventText = itemView.findViewById<TextView>(R.id.event_conflict_text)


        fun dataBinding(
            event: Event,
            eventsList: List<Event>,
            onClicked: (Event, List<Event>, RecyclerView, Button, TextView) -> Unit
        ) {
            titleVieww.text = event.title
            startDateView.text = "Start at: ${event.start}"
            endDateView.text = "End at: ${event.end}"
            conflictBtn.setOnClickListener {
                onClicked(
                    event,
                    eventsList,
                    conflictedEventView,
                    conflictBtn,
                    confictedEventText
                )
            }
        }
    }
}