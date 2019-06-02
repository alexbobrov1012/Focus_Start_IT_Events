package com.example.iteventscheckin.presentation.members;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iteventscheckin.R;

import com.example.iteventscheckin.presentation.OnCheckBoxClickListener;
import com.example.iteventscheckin.presentation.memberinfo.MemberInfoActivity;
import com.example.iteventscheckin.models.Member;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import com.example.iteventscheckin.presentation.OnItemListClickListener;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MembersActivity extends AppCompatActivity implements OnCheckBoxClickListener {
    MembersAdapter adapter;

    MembersViewModel viewModel;

    ProgressBar progressBar;

    SearchView searchView;

    Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        int eventId = 106;

        viewModel = ViewModelProviders.of(this, new ViewModelFactory(eventId)).get(MembersViewModel.class);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initSearch();

        initRefresh();

        initRecycleView();

        progressBar = findViewById(R.id.members_progress);

        progressBar.setVisibility(View.VISIBLE);
        viewModel.fetchMembers();
        viewModel.getAllMembers().observe(this, new Observer<List<Member>>() {
            @Override
            public void onChanged(List<Member> members) {
                adapter.setMembers(members);
                Log.d("DEBUG", "setMembers");
                progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.getResponseText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void initRefresh() {
        refreshButton = findViewById(R.id.membersRefreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.fetchMembers();
            }
        });
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.membersRecycleView);
        adapter = new MembersAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initSearch() {
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.search(newText);
                return false;
            }
        });

    }

    @Override
    public void onItemListClick(int adapterPosition) {
        Intent intent = new Intent(this, MemberInfoActivity.class);
        intent.putExtra("member", adapter.getMemberItem(adapterPosition));
        startActivity(intent);
        Toast.makeText(this, "Clicked",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckBoxClicked(int adapterPosition, boolean isChecked) {
        viewModel.confirmVisit(adapter.getMemberid(adapterPosition), isChecked);
        Log.d("DEBUG", "d " + isChecked);
        //viewModel.updateMembersFromDB();
    }
}
