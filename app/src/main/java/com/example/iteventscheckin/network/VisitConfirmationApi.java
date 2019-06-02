package com.example.iteventscheckin.network;

import com.example.iteventscheckin.models.RequestVisit;
import com.example.iteventscheckin.models.ResponseVisit;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface VisitConfirmationApi {

    @POST("/api/v1/Registration/members/event/{eventId}/confirmation")
    Single<ResponseVisit> confirmVisit(@Path("eventId") int eventId,
                                       @Body List<RequestVisit> requestVisit,
                                       @Query("token") String token);
}
