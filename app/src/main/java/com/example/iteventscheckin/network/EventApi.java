package com.example.iteventscheckin.network;

import com.example.iteventscheckin.models.Event;
import io.reactivex.Single;
import retrofit2.http.GET;

import java.util.List;

public interface EventApi {
    @GET("/api/v1/Events/registration?token=cftte@mtest20!9")
    Single<List<Event>> getAllEvents();
}
