package com.example.iteventscheckin

import android.util.Log
import com.example.iteventscheckin.database.AppRoomDatabase
import com.example.iteventscheckin.models.Event
import com.example.iteventscheckin.models.Member
import com.example.iteventscheckin.models.RequestVisit
import com.example.iteventscheckin.models.ResponseVisit
import com.example.iteventscheckin.network.EventApi
import com.example.iteventscheckin.network.MemberApi
import com.example.iteventscheckin.network.RetrofitProvider
import com.example.iteventscheckin.network.VisitConfirmationApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

import java.util.concurrent.TimeUnit

class Repository(
    private val retrofitProvider: RetrofitProvider,
    private val eventApi: EventApi,
    private val memberApi: MemberApi,
    private val visitConfirmationApi: VisitConfirmationApi
) {
    private val database = App.appInstance.roomDatabase

    private val allEventsFromNet: Single<List<Event>>
        get() = eventApi.getAllEvents(retrofitProvider.token)
            .subscribeOn(Schedulers.io())
            .flatMap { events ->
                if (events.isNotEmpty()) {
                    putEventsToDatabase(events)
                } else {
                    Single.just(events)
                }
            }

    private val allEventsFromDataBase: Single<List<Event>>
        get() = database!!.eventDao().getAllEvents()
            .subscribeOn(Schedulers.io())

    val allEvents: Single<List<Event>>
        get() = allEventsFromNet
            .onErrorResumeNext {
                Log.d("DEBUG", "Connection lost, loading local data.")
                App.appInstance.repository!!
                    .allEventsFromDataBase
            }

    private fun putEventsToDatabase(events: List<Event>): Single<List<Event>> {
        return database!!.eventDao().insert(events)
            .subscribeOn(Schedulers.io())
            .andThen(Single.just(events))
    }

    ///////////////////////////////

    private fun getAllMembersFromNet(eventId: Int): Single<List<Member>> {
        return memberApi.getAllMembers(eventId, retrofitProvider.token)
            .subscribeOn(Schedulers.io())
            .flatMap { members -> Observable.fromIterable(members) }
            .map { member ->
                member.eventId = eventId
                member
            }
            .toList()
            .flatMap { members -> putMembersToDatabase(members) }
    }

    fun getAllMembersFromDataBase(eventId: Int): Single<List<Member>> {
        return database!!.membersDao().getAllMembers(eventId)
            .subscribeOn(Schedulers.io())
    }

    fun getAllMembers(eventId: Int): Single<List<Member>> {
        return getAllMembersFromNet(eventId)
            .onErrorResumeNext {
                Log.d("DEBUG", "Connection lost, loading local data.$eventId")
                App.appInstance.repository!!
                    .getAllMembersFromDataBase(eventId)
            }
    }

    private fun putMembersToDatabase(members: List<Member>): Single<List<Member>> {
        return database!!.membersDao().insert(members)
            .subscribeOn(Schedulers.io())
            .andThen(Single.just(members))
    }


    fun confirmVisit(eventId: Int, memberId: Int, isChecked: Boolean): Single<ResponseVisit> {
        val requestVisit = RequestVisit(memberId, isChecked, DateUtils.getCurrentDate())
        return visitConfirmationApi.confirmVisit(eventId, Arrays.asList(requestVisit), retrofitProvider.token)
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                Log.d("DEBUG", "CONFIRM SUCC ${it.result}")
                updateDatabase(memberId, isChecked)
            }
            .onErrorReturn {
                Log.d("DEBUG", "CONFIRM ERROR")
                ResponseVisit(false, it.message)
            }
    }

    private fun updateDatabase(memberId: Int, isChecked: Boolean): Boolean {
        return database!!.membersDao().updateVisited(memberId, isChecked)
            .subscribeOn(Schedulers.io())
            .blockingAwait(4, TimeUnit.SECONDS)
    }

    fun getSearchResult(input: String): Observable<List<Member>> {
        return database!!.membersDao().searchMembers(input)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}
