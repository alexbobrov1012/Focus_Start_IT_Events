package com.example.iteventscheckin.presentation.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iteventscheckin.models.Event;
import com.example.iteventscheckin.presentation.OnItemListClickListener;
import com.example.iteventscheckin.R;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder> {
    private List<Event> events;

    private OnItemListClickListener listener;

    EventsAdapter(OnItemListClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_item, parent, false);
        return new EventsViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        holder.bind(events.get(position));
    }

    @Override
    public int getItemCount() {
        if (events == null)
            return 0;
        return events.size();
    }

    public void setEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public int getEventId(int position) {
        return events.get(position).getId();
    }


}
