package com.example.iteventscheckin

import android.app.Application
import androidx.room.Room
import com.example.iteventscheckin.database.AppRoomDatabase
import com.example.iteventscheckin.network.EventApi
import com.example.iteventscheckin.network.MemberApi
import com.example.iteventscheckin.network.RetrofitProvider
import com.example.iteventscheckin.network.VisitConfirmationApi

class App : Application() {

    var repository: Repository? = null
        private set

    var roomDatabase: AppRoomDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        roomDatabase = Room.databaseBuilder(
            this,
            AppRoomDatabase::class.java, "EventsDB"
        )
            .build()
        val retrofitProvider = RetrofitProvider()
        val eventApi = retrofitProvider.retrofit.create(EventApi::class.java)
        val memberApi = retrofitProvider.retrofit.create(MemberApi::class.java)
        val visitConfirmationApi = retrofitProvider.retrofit.create(VisitConfirmationApi::class.java)
        repository = Repository(retrofitProvider, eventApi, memberApi, visitConfirmationApi)

    }

    companion object {

        lateinit var appInstance: App
    }
}
