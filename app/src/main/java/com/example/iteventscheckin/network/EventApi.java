package com.example.iteventscheckin.network;

import com.example.iteventscheckin.room.Event;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface EventApi {
    @GET("/api/v1/Events/registration?token=cftte@mtest20!9")
    Single<List<Event>> getAllEvents();
}
