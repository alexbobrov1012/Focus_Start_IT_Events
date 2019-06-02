package com.example.iteventscheckin.presentation.members;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.iteventscheckin.App;
import com.example.iteventscheckin.models.Event;
import com.example.iteventscheckin.models.Member;
import com.example.iteventscheckin.models.RequestVisit;
import com.example.iteventscheckin.models.ResponseVisit;
import io.reactivex.Single;
import okhttp3.Response;

import java.util.List;

public class MembersViewModel extends ViewModel {

    private final int eventId;

    private MutableLiveData<List<Member>> allMembers = new MutableLiveData<>();

    private MutableLiveData<String> responseText = new MutableLiveData<>();


    MembersViewModel(int eventId) {
        this.eventId = eventId;
    }

    MutableLiveData<List<Member>> getAllMembers() {
        return allMembers;
    }

    MutableLiveData<String> getResponseText() {
        return responseText;
    }

    private String getResponseInfo(ResponseVisit responseVisit) {
        return responseVisit.isResult() ? "Success" : "Failure: " +
                responseVisit.getMessage();
    }

    void search(String newText) {
        App.appInstance.getRepository().getSearchResult(newText + "%")
                .subscribe(result -> {
                    allMembers.postValue(result);
                });
    }

    void fetchMembers() {
        App.appInstance.getRepository().getAllMembers(eventId)
                .subscribe(members -> {
                    allMembers.postValue(members);
                });
    }

    private void updateMembersFromDB() {
        App.appInstance.getRepository().getAllMembersFromDataBase(eventId)
                .subscribe(members -> {
                    allMembers.postValue(members);
                });
    }

    void confirmVisit(int memberId, boolean isChecked) {
        App.appInstance.getRepository().confirmVisit(eventId, memberId, isChecked)
                .subscribe(responseVisit -> {
                    responseText.postValue(getResponseInfo(responseVisit));
                    updateMembersFromDB();
                });
    }
}
