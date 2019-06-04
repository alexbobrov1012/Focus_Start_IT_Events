package com.example.iteventscheckin.presentation.members

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iteventscheckin.App
import com.example.iteventscheckin.models.Event
import com.example.iteventscheckin.models.Member
import com.example.iteventscheckin.models.RequestVisit
import com.example.iteventscheckin.models.ResponseVisit
import io.reactivex.Single
import okhttp3.Response

class MembersViewModel internal constructor(private val eventId: Int) : ViewModel() {

    val allMembers = MutableLiveData<List<Member>>()

    val responseText = MutableLiveData<String>()

    private fun getResponseInfo(responseVisit: ResponseVisit): String {
        Log.d("DEBUG", responseVisit.result.toString())
        val result : String
        if (responseVisit.result) {
            result = "Success: " + responseVisit.message!!
        } else {
            result = "Failure: " + responseVisit.message!!
        }
        return result
    }

    fun search(newText: String) {
        App.appInstance.repository!!.getSearchResult("$newText%")
            .subscribe { result -> allMembers.postValue(result) }
    }

    fun fetchMembers() {
        App.appInstance.repository!!.getAllMembers(eventId)
            .subscribe { members -> allMembers.postValue(members) }
    }

    private fun updateMembersFromDB() {
        App.appInstance.repository!!.getAllMembersFromDataBase(eventId)
            .subscribe { members -> allMembers.postValue(members) }
    }

    fun confirmVisit(memberId: Int, isChecked: Boolean) {
        App.appInstance.repository!!.confirmVisit(eventId, memberId, isChecked)
            .subscribe { responseVisit ->
                responseText.postValue(getResponseInfo(responseVisit))
                updateMembersFromDB()
            }
    }
}
