package com.example.iteventscheckin.presentation.events;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.iteventscheckin.App;
import com.example.iteventscheckin.models.Event;
import io.reactivex.Single;

import java.util.List;

public class EventsViewModel extends ViewModel {

    private MutableLiveData<List<Event>> allEvents = new MutableLiveData<>();

    public MutableLiveData<String> getResponseText() {
        return responseText;
    }

    private MutableLiveData<String> responseText = new MutableLiveData<>();

    public EventsViewModel() {

    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    public void fetchEvents() {
        App.appInstance.getRepository().getAllEvents()
                .subscribe(events-> {
                        allEvents.postValue(events);
                    }
                );
    }

}