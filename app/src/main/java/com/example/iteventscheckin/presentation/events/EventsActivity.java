package com.example.iteventscheckin.presentation.events;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iteventscheckin.room.Event;
import com.example.iteventscheckin.presentation.OnItemListClickListener;
import com.example.iteventscheckin.R;
import com.example.iteventscheckin.presentation.members.MembersActivity;

import java.util.List;

public class EventsActivity extends AppCompatActivity implements OnItemListClickListener {

    EventsAdapter adapter;

    EventsViewModel viewModel;

    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.event_progress);
        RecyclerView recyclerView = findViewById(R.id.eventsRecycleView);
        adapter = new EventsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this).get(EventsViewModel.class);
        progressBar.setVisibility(View.VISIBLE);
        viewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                adapter.setEvents(events);
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onItemListClick(int adapterPosition) {
        Toast.makeText(this, "Clicked",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MembersActivity.class);
        startActivity(intent);
    }
}
