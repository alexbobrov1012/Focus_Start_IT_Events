package com.example.iteventscheckin.presentation.events;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.iteventscheckin.database.EventDao;

public class EventViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private EventDao eventDao;

    public EventViewModelFactory(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EventsViewModel();
    }
}
