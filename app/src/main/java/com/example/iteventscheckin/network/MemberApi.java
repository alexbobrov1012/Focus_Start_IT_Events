package com.example.iteventscheckin.network;

import com.example.iteventscheckin.room.Member;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface MemberApi {
    @GET("/api/v1/Registration/members/event/{eventId}?token=cftte@mtest20!9")
    Single<List<Member>> getAllMembers(@Path("eventId") int eventId);
}
