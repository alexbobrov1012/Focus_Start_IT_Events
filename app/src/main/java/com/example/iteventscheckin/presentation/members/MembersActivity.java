package com.example.iteventscheckin.presentation.members;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.InvalidationTracker;
import com.example.iteventscheckin.R;
import com.example.iteventscheckin.room.Member;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import com.example.iteventscheckin.presentation.OnItemListClickListener;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MembersActivity extends AppCompatActivity implements OnItemListClickListener {
    MembersAdapter adapter;

    MembersViewModel viewModel;

    ProgressBar progressBar;

    SearchView searchView;

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int eventId = 106;
        progressBar = findViewById(R.id.members_progress);
        searchView = findViewById(R.id.searchView);
        RecyclerView recyclerView = findViewById(R.id.membersRecycleView);
        adapter = new MembersAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(eventId)).get(MembersViewModel.class);
        progressBar.setVisibility(View.VISIBLE);

        mDisposable.add(viewModel.getAllMembers()
                .subscribeWith(new DisposableSingleObserver<List<Member>>() {
                    @Override
                    public void onSuccess(List<Member> memberList) {
                        progressBar.setVisibility(View.GONE);
                        // Log.d("DEBUG", memberList.get(0).toString() + "!");
                        adapter.setMembers(memberList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Toast.makeText(this, "Clicked",Toast.LENGTH_SHORT).show();
                    }
                }));
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        emitter.onNext(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        emitter.onNext(newText);
                        return false;
                    }
                });
            }
        })
                .debounce(250, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                viewModel.getSearchResult("%"+s+"%").subscribeWith(new DisposableSingleObserver<List<Member>>() {
                    @Override
                    public void onSuccess(List<Member> memberList) {
                        adapter.setMembers(memberList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            }
        });

    }
    @Override
    public void onItemListClick(int adapterPosition) {
        Toast.makeText(this, "Clicked",Toast.LENGTH_SHORT).show();
    }

}
