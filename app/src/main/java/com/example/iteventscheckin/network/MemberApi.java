package com.example.iteventscheckin.network;

import com.example.iteventscheckin.models.Member;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface MemberApi {
    @GET("/api/v1/Registration/members/event/{eventId}")
    Observable<List<Member>> getAllMembers(
            @Path("eventId") int eventId,
            @Query("token") String token);
}
