package com.example.iteventscheckin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.iteventscheckin.models.Member;
import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface MembersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Member member);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(List<Member> memberList);

    @Query("select * from members_table where eventId = :eventId")
    Single<List<Member>> getAllMembers(int eventId);

    @Query("select * from members_table where id = :id")
    Single<Member> getMemberById(int id);

    @Query("select * from members_table where lastName LIKE :input OR firstName LIKE :input OR patronymic LIKE :input")
    Single<List<Member>> searchMembers(String input);

    @Query("update members_table set isVisited = :state WHERE id = :id")
    Completable updateVisited(int id, boolean state);

    @Query("delete from members_table")
    void deleteAll();
}
