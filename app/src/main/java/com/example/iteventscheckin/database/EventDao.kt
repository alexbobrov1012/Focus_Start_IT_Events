package com.example.iteventscheckin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iteventscheckin.models.Event
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface EventDao {

    @Query("select * from events_table")
    fun getAllEvents(): Single<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: Event): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(eventList: List<Event>): Completable

    @Query("delete from events_table")
    fun deleteAll()
}
