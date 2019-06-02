package com.example.iteventscheckin;

import android.util.Log;
import com.example.iteventscheckin.database.AppRoomDatabase;
import com.example.iteventscheckin.models.Event;
import com.example.iteventscheckin.models.Member;
import com.example.iteventscheckin.models.RequestVisit;
import com.example.iteventscheckin.models.ResponseVisit;
import com.example.iteventscheckin.network.EventApi;
import com.example.iteventscheckin.network.MemberApi;
import com.example.iteventscheckin.network.RetrofitProvider;
import com.example.iteventscheckin.network.VisitConfirmationApi;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Repository {

    private RetrofitProvider retrofitProvider;

    private EventApi eventApi;

    private MemberApi memberApi;

    private VisitConfirmationApi visitConfirmationApi;

    private final CompositeDisposable disposable = new CompositeDisposable();

    private AppRoomDatabase database = App.appInstance.getRoomDatabase();

    public Repository(RetrofitProvider retrofitProvider, EventApi eventApi, MemberApi memberApi, VisitConfirmationApi visitConfirmationApi) {
        this.retrofitProvider = retrofitProvider;
        this.eventApi = eventApi;
        this.memberApi = memberApi;
        this.visitConfirmationApi = visitConfirmationApi;
    }


    public Single<List<Event>> getAllEventsFromNet() {
        return eventApi.getAllEvents(retrofitProvider.token)
                .subscribeOn(Schedulers.io())
                .flatMap(events -> {
                    if(events.size() != 0) {
                        return putEventsToDatabase(events);
                    } else {
                        return Single.just(events);
                    }
                });
    }

    public Single<List<Event>> getAllEventsFromDataBase() {
        return database.eventDao().getAllEvents()
                .subscribeOn(Schedulers.io());
    }

    public Single<List<Event>> getAllEvents() {
        return getAllEventsFromNet()
                .onErrorResumeNext(throwable -> {
                    Log.d("DEBUG","Connection lost, loading local data.");
                    return App.appInstance.getRepository()
                            .getAllEventsFromDataBase();
                });
    }

    private Single<List<Event>> putEventsToDatabase(List<Event> events) {
        return database.eventDao().insert(events)
                .subscribeOn(Schedulers.io())
                .andThen(Single.just(events));
    }

    ///////////////////////////////

    private Single<List<Member>> getAllMembersFromNet(int eventId) {
        return memberApi.getAllMembers(eventId, retrofitProvider.token)
                .subscribeOn(Schedulers.io())
                .flatMap(members -> {
                    return Observable.fromIterable(members);
                })
                .map(member -> {
                    member.setEventId(eventId);
                    //Log.d("DEBUG", member.getId() + " " + member.getEventId()+ " id");
                    return member;
                })
                .toList()
                .flatMap(members -> {
                    return putMembersToDatabase(members);
                });
    }

    public Single<List<Member>> getAllMembersFromDataBase(int eventId) {
        return database.membersDao().getAllMembers(eventId)
                .subscribeOn(Schedulers.io());
    }

    public Single<List<Member>> getAllMembers(int eventId) {
        return getAllMembersFromNet(eventId)
                .onErrorResumeNext(throwable -> {
                    Log.d("DEBUG","Connection lost, loading local data." + eventId);
                    return App.appInstance.getRepository()
                            .getAllMembersFromDataBase(eventId);
                });
    }

    private Single<List<Member>> putMembersToDatabase(List<Member> members) {
        return database.membersDao().insert(members)
                .subscribeOn(Schedulers.io())
                .andThen(Single.just(members));
    }


    public Single<ResponseVisit> confirmVisit(int eventId, int memberId, boolean isChecked) {
        RequestVisit requestVisit = new RequestVisit(memberId, isChecked, "2019-04-12T22:25:56");
        return visitConfirmationApi.confirmVisit(eventId, Arrays.asList(requestVisit), retrofitProvider.token)
                    .subscribeOn(Schedulers.io())
                    .doOnSuccess(success -> {
                        updateDatabse(memberId, isChecked);
                    })
                    .onErrorReturn(new Function<Throwable, ResponseVisit>() {
                        @Override
                        public ResponseVisit apply(Throwable throwable) throws Exception {
                            return new ResponseVisit(false, throwable.getMessage());
                        }
                    });
                    //.delaySubscription(updateDatabse(memberId, ));
    }

    private boolean updateDatabse(int memberId, boolean isChecked) {
        return database.membersDao().updateVisited(memberId, isChecked)
                .subscribeOn(Schedulers.io())
                .blockingAwait(4, TimeUnit.SECONDS);
    }

    public Single<List<Member>> getSearchResult(String input) {
        return database.membersDao().searchMembers(input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
