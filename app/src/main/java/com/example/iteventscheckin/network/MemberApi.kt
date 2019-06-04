package com.example.iteventscheckin.network

import com.example.iteventscheckin.models.Member
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MemberApi {
    @GET("/api/v1/Registration/members/event/{eventId}")
    fun getAllMembers(
        @Path("eventId") eventId: Int,
        @Query("token") token: String
    ): Observable<List<Member>>
}
