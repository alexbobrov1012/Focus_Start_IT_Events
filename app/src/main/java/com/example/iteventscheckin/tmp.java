package com.example.iteventscheckin;

import androidx.lifecycle.ViewModel;
import com.example.iteventscheckin.models.Event;

import java.util.List;

public class tmp extends ViewModel {
    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    List<Event> eventList;


}
