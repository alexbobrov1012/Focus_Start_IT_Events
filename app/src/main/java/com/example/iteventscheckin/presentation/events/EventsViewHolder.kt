package com.example.iteventscheckin.presentation.events

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iteventscheckin.DateUtils
import com.example.iteventscheckin.models.Event
import com.example.iteventscheckin.presentation.OnItemListClickListener
import com.example.iteventscheckin.R

class EventsViewHolder(itemView: View, private val listener: OnItemListClickListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val titleView: TextView

    private val descView: TextView

    private val dateView: TextView

    init {
        titleView = itemView.findViewById(R.id.eventTitle)
        descView = itemView.findViewById(R.id.eventDesc)
        dateView = itemView.findViewById(R.id.eventDate)
        itemView.setOnClickListener(this)
    }

    fun bind(model: Event) {
        titleView.text = model.title
        descView.text = model.description
        dateView.text = String.format("%s - %s", DateUtils.dateFromFormat(model.date?.start),
            DateUtils.dateFromFormat(model.date?.end))
    }

    override fun onClick(v: View) {
        listener.onItemListClick(adapterPosition)
    }
}
