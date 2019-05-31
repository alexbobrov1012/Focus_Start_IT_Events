package com.example.iteventscheckin.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.iteventscheckin.models.Event;
import com.example.iteventscheckin.models.Member;

@Database(entities = {Event.class, Member.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract EventDao eventDao();
    public abstract MembersDao membersDao();
}
