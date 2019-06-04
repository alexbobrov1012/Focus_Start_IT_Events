package com.example.iteventscheckin.presentation.memberinfo

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.iteventscheckin.databinding.ActivityMemberInfoBinding
import com.example.iteventscheckin.models.Member
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.iteventscheckin.R

class MemberInfoActivity : AppCompatActivity() {

    internal lateinit var binding: ActivityMemberInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val member = intent.getSerializableExtra("member") as Member
        binding = DataBindingUtil.setContentView<ActivityMemberInfoBinding>(this, R.layout.activity_member_info)
        binding.setMember(member)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //toolbar.setTitle(R.string.title_activity_member_info);
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
