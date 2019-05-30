package com.example.iteventscheckin.presentation.events;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iteventscheckin.room.Event;
import com.example.iteventscheckin.presentation.OnItemListClickListener;
import com.example.iteventscheckin.R;

public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView titleView;

    private TextView descView;

    private TextView dateView;

    private OnItemListClickListener listener;

    public EventsViewHolder(@NonNull View itemView, OnItemListClickListener listener) {
        super(itemView);
        this.listener = listener;
        titleView = itemView.findViewById(R.id.eventTitle);
        descView = itemView.findViewById(R.id.eventDesc);
        dateView = itemView.findViewById(R.id.eventDate);
        itemView.setOnClickListener(this);
    }

    public void bind(Event model) {
        titleView.setText(model.getTitle());
        descView.setText(model.getDescription());
        dateView.setText("TODO:");
    }

    @Override
    public void onClick(View v) {
        listener.onItemListClick(getAdapterPosition());
    }
}
