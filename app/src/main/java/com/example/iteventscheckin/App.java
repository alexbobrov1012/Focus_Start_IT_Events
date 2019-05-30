package com.example.iteventscheckin;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.iteventscheckin.network.RetrofitProvider;
import com.example.iteventscheckin.room.MyRoomDatabase;

public class App extends Application {

    public static App appInstance;

    private Repository repository;

    private MyRoomDatabase myRoomDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        myRoomDatabase = Room.databaseBuilder(this,
                MyRoomDatabase.class, "EventsDB")
                .build();
        RetrofitProvider retrofitProvider = new RetrofitProvider();
        repository = new Repository(retrofitProvider);

    }

    public Repository getRepository() {
        return repository;
    }

    public MyRoomDatabase getMyRoomDatabase() {
        return myRoomDatabase;
    }
}
