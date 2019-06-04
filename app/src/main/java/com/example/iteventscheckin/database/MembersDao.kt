package com.example.iteventscheckin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iteventscheckin.models.Member
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MembersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(member: Member): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memberList: List<Member>): Completable

    @Query("select * from members_table where eventId = :eventId")
    fun getAllMembers(eventId: Int): Single<List<Member>>

    @Query("select * from members_table where id = :id")
    fun getMemberById(id: Int): Single<Member>

    @Query("select * from members_table where lastName LIKE :input OR firstName LIKE :input OR patronymic LIKE :input")
    fun searchMembers(input: String): Observable<List<Member>>

    @Query("update members_table set isVisited = :state WHERE id = :id")
    fun updateVisited(id: Int, state: Boolean): Completable

    @Query("delete from members_table")
    fun deleteAll()
}
