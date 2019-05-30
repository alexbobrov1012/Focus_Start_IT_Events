package com.example.iteventscheckin.presentation.members;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iteventscheckin.R;
import com.example.iteventscheckin.room.Member;
import com.example.iteventscheckin.presentation.OnItemListClickListener;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersViewHolder> {

    private List<Member> members;

    private OnItemListClickListener listener;

    MembersAdapter(OnItemListClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.members_item, parent, false);
        return new MembersViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {
        holder.bind(members.get(position));
    }

    @Override
    public int getItemCount() {
        if (members == null)
            return 0;
        return members.size();
    }

    public void setMembers(List<Member> members) {
        this.members = members;
        notifyDataSetChanged();
    }

    public int getMemberId(int position) {
        return members.get(position).getId();
    }

}
