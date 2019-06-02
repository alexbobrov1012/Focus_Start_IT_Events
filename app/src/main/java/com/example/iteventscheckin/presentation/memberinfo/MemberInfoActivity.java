package com.example.iteventscheckin.presentation.memberinfo;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import com.example.iteventscheckin.databinding.ActivityMemberInfoBinding;
import com.example.iteventscheckin.models.Member;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.iteventscheckin.R;

public class MemberInfoActivity extends AppCompatActivity {

    ActivityMemberInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Member member = (Member) getIntent().getSerializableExtra("member");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_member_info);
        binding.setMember(member);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle(R.string.title_activity_member_info);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
