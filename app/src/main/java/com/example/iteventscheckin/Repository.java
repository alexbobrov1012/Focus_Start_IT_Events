package com.example.iteventscheckin;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.iteventscheckin.models.Event;
import com.example.iteventscheckin.models.Member;
import com.example.iteventscheckin.network.EventApi;
import com.example.iteventscheckin.network.MemberApi;
import com.example.iteventscheckin.network.RetrofitProvider;
import com.example.iteventscheckin.room.MyRoomDatabase;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class Repository {

    private RetrofitProvider retrofitProvider;

    public Repository(RetrofitProvider retrofitProvider) {
        this.retrofitProvider = retrofitProvider;
    }

    private final CompositeDisposable disposable = new CompositeDisposable();

    private MyRoomDatabase database = App.appInstance.getMyRoomDatabase();


    public LiveData<List<Event>> getAllEvents() {
        final MutableLiveData<List<Event>> data = new MutableLiveData<>();
        disposable.add(retrofitProvider.getRetrofit().create(EventApi.class).getAllEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Event>>() {
                    @Override
                    public void onSuccess(List<Event> eventList) {
                        database.eventDao().insert(eventList)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DEBUG", "ERROR_NET_EVENTS" + e.getMessage());
                    }
                }));

        disposable.add(database.eventDao().getAllEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Event>>() {
                    @Override
                    public void onSuccess(List<Event> eventList) {
                        data.setValue(eventList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DEBUG", "ERROR_DB_EVENTS" + e.getMessage());
                    }
                }));
       // disposable.clear();
        return data;
    }
    
    public LiveData<Member> getMemberById(int memberId) {
        final MutableLiveData<Member> data = new MutableLiveData<>();
        disposable.add(database.membersDao().getMemberById(memberId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Member>() {
                    @Override
                    public void onSuccess(Member member) {
                        data.setValue(member);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
        return data;
        
    }

    public Single<List<Member>> getSearchResult(String input) {
        return database.membersDao().searchMembers(input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Member>> getAllMembers(int eventId) {
        final MutableLiveData<List<Member>> data = new MutableLiveData<>();
        disposable.add(retrofitProvider.getRetrofit().create(MemberApi.class).getAllMembers(eventId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<List<Member>>() {
                        @Override
                        public void onSuccess(List<Member> memberList) {
                            Log.d("DEBUG", "retrofit ok");
                            database.membersDao().insert(memberList)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("DEBUG", "ERROR_NET_MEMBERS" + e.getMessage());
                        }
                    }));

       return database.membersDao().getAllMembers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
