package com.example.iteventscheckin.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.iteventscheckin.models.Event;
import com.example.iteventscheckin.models.Member;

@Database(entities = {Event.class, Member.class}, version = 1)
public abstract class AppRoomDatabase extends androidx.room.RoomDatabase {
    public abstract EventDao eventDao();
    public abstract MembersDao membersDao();
}
