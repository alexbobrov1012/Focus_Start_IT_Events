package com.example.iteventscheckin.presentation.events

import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iteventscheckin.models.Event
import com.example.iteventscheckin.presentation.OnItemListClickListener
import com.example.iteventscheckin.R
import com.example.iteventscheckin.presentation.members.MembersActivity

class EventsActivity : AppCompatActivity(), OnItemListClickListener {

    private lateinit var adapter: EventsAdapter

    private lateinit var viewModel: EventsViewModel

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.event_progress)

        initAdapter()

        viewModel = ViewModelProviders.of(this).get(EventsViewModel::class.java)
        progressBar.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        viewModel.fetchEvents()
        viewModel.allEvents.observe(this, Observer<List<Event>>{ events ->
            adapter.setEvents(events)
            progressBar.visibility = View.GONE
        })

    }

    fun initAdapter() {
        val recyclerView = findViewById<RecyclerView>(R.id.eventsRecycleView)
        adapter = EventsAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemListClick(adapterPosition: Int) {
        val intent = Intent(this, MembersActivity::class.java)
        intent.putExtra("eventId", adapter.getEventId(adapterPosition))
        startActivity(intent)
    }
}
