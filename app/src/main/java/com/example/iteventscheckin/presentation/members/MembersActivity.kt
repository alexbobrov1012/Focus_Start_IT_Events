package com.example.iteventscheckin.presentation.members

import android.app.ActivityManager
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iteventscheckin.R

import com.example.iteventscheckin.presentation.OnCheckBoxClickListener
import com.example.iteventscheckin.presentation.memberinfo.MemberInfoActivity
import com.example.iteventscheckin.models.Member
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import com.example.iteventscheckin.presentation.OnItemListClickListener
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableSingleObserver
import java.util.concurrent.TimeUnit

class MembersActivity : AppCompatActivity(), OnCheckBoxClickListener {
    private lateinit var adapter: MembersAdapter

    private lateinit var viewModel: MembersViewModel

    private lateinit var progressBar: ProgressBar

    private lateinit var searchView: SearchView

    private lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)

        val eventId = 106

        viewModel = ViewModelProviders.of(this, ViewModelFactory(eventId)).get(MembersViewModel::class.java)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        initSearch()

        initRefresh()

        initRecycleView()

        progressBar = findViewById(R.id.members_progress)

        progressBar.visibility = View.VISIBLE
        viewModel.fetchMembers()
        viewModel.allMembers.observe(this, Observer<List<Member>> { members ->
            adapter.setMembers(members)
            Log.d("DEBUG", "setMembers")
            progressBar.visibility = View.GONE
        })

        viewModel.responseText
            .observe(this, Observer<String> { s -> Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show() })


    }

    private fun initRefresh() {
        refreshButton = findViewById(R.id.membersRefreshButton)
        refreshButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            viewModel.fetchMembers()
        }
    }

    private fun initRecycleView() {
        val recyclerView = findViewById<RecyclerView>(R.id.membersRecycleView)
        adapter = MembersAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initSearch() {
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return false
            }
        })

    }

    override fun onItemListClick(adapterPosition: Int) {
        val intent = Intent(this, MemberInfoActivity::class.java)
        intent.putExtra("member", adapter.getMemberItem(adapterPosition))
        startActivity(intent)
    }

    override fun onCheckBoxClicked(adapterPosition: Int, isChecked: Boolean) {
        viewModel.confirmVisit(adapter.getMemberid(adapterPosition), isChecked)
        Log.d("DEBUG", "d $isChecked")
    }
}
