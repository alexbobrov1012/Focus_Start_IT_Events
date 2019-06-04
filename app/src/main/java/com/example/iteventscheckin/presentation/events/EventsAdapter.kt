package com.example.iteventscheckin.presentation.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iteventscheckin.models.Event
import com.example.iteventscheckin.presentation.OnItemListClickListener
import com.example.iteventscheckin.R

class EventsAdapter internal constructor(private val listener: OnItemListClickListener) :
    RecyclerView.Adapter<EventsViewHolder>() {
    private var events: List<Event>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.events_item, parent, false)
        return EventsViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(events!![position])
    }

    override fun getItemCount(): Int {
        return if (events == null) 0 else events!!.size
    }

    fun setEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    fun getEventId(position: Int): Int {
        return events!![position].id
    }


}
