package com.example.iteventscheckin.presentation.events;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.iteventscheckin.App;
import com.example.iteventscheckin.room.Event;

import java.util.List;

public class EventsViewModel extends ViewModel {

    private LiveData<List<Event>> allEvents;

    public EventsViewModel() {
        this.allEvents = App.appInstance.getRepository().getAllEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }
}