package com.example.iteventscheckin.network

import com.example.iteventscheckin.models.Event
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {
    @GET("/api/v1/Events/registration")
    fun getAllEvents(
        @Query("token") token: String
    ): Single<List<Event>>
}
