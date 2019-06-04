package com.example.iteventscheckin.presentation.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iteventscheckin.App
import com.example.iteventscheckin.models.Event
import io.reactivex.Single

class EventsViewModel : ViewModel() {

    internal val allEvents = MutableLiveData<List<Event>>()

    fun fetchEvents() {
        App.appInstance.repository!!.allEvents
            .subscribe { events -> allEvents.postValue(events) }
    }

}