package com.example.iteventscheckin.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface MembersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Member member);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(List<Member> memberList);

    @Query("select * from members_table")
    Single<List<Member>> getAllMembers();

    @Query("select * from members_table where lastName LIKE :input OR firstName LIKE :input OR patronymic LIKE :input")
    Single<List<Member>> searchMembers(String input);

    @Query("delete from members_table")
    void deleteAll();
}
