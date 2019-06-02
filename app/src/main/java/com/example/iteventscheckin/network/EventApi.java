package com.example.iteventscheckin.network;

import com.example.iteventscheckin.models.Event;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface EventApi {
    @GET("/api/v1/Events/registration")
    Single<List<Event>> getAllEvents(
            @Query("token") String token);
}
