package com.example.iteventscheckin.presentation.members;

import androidx.lifecycle.ViewModel;
import com.example.iteventscheckin.App;
import com.example.iteventscheckin.models.Member;
import io.reactivex.Single;

import java.util.List;

public class MembersViewModel extends ViewModel {
    private Single<List<Member>> allMembers;

    public MembersViewModel(int eventId) {
        this.allMembers = App.appInstance.getRepository().getAllMembers(eventId);
    }

    public Single<List<Member>> getAllMembers() {
        return allMembers;
    }

    public Single<List<Member>> getSearchResult(String input) {
        return App.appInstance.getRepository().getSearchResult(input);
    }
}
