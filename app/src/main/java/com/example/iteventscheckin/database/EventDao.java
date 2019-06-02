package com.example.iteventscheckin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.iteventscheckin.models.Event;
import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Event event);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(List <Event> eventList);

    @Query("select * from events_table")
    Single<List<Event>> getAllEvents();

    @Query("delete from events_table")
    void deleteAll();
}
