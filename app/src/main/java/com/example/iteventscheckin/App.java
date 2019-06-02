package com.example.iteventscheckin;

import android.app.Application;
import androidx.room.Room;
import com.example.iteventscheckin.database.AppRoomDatabase;
import com.example.iteventscheckin.network.EventApi;
import com.example.iteventscheckin.network.MemberApi;
import com.example.iteventscheckin.network.RetrofitProvider;
import com.example.iteventscheckin.network.VisitConfirmationApi;

public class App extends Application {

    public static App appInstance;

    private Repository repository;

    private AppRoomDatabase roomDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        roomDatabase = Room.databaseBuilder(this,
                AppRoomDatabase.class, "EventsDB")
                .build();
        RetrofitProvider retrofitProvider = new RetrofitProvider();
        EventApi eventApi = retrofitProvider.getRetrofit().create(EventApi.class);
        MemberApi memberApi = retrofitProvider.getRetrofit().create(MemberApi.class);
        VisitConfirmationApi visitConfirmationApi = retrofitProvider.getRetrofit().create(VisitConfirmationApi.class);
        repository = new Repository(retrofitProvider, eventApi, memberApi, visitConfirmationApi);

    }

    public Repository getRepository() {
        return repository;
    }

    public AppRoomDatabase getRoomDatabase() {
        return roomDatabase;
    }
}
