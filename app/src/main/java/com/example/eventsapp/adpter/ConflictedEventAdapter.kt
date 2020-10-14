package com.example.eventsapp.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.R
import com.example.eventsapp.models.Event

class ConflictedEventAdapter(private val context: Context, private val events: ArrayList<Event>) :
    RecyclerView.Adapter<ConflictedEventAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_conflicted, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.dataBinding(events.get(position))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var titleView = itemView.findViewById<TextView>(R.id.event_title_conflict)


        fun dataBinding(
            event: Event
        ) {
            titleView.text = event.title
        }
    }
}