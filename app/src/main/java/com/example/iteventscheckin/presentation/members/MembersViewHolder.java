package com.example.iteventscheckin.presentation.members;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iteventscheckin.R;
import com.example.iteventscheckin.models.Member;
import com.example.iteventscheckin.presentation.OnCheckBoxClickListener;
import com.example.iteventscheckin.presentation.OnItemListClickListener;

public class MembersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView firstNameView;

    private TextView lastNameView;

    private TextView patronymicView;

    private CheckBox visitedCheckBox;

    private OnCheckBoxClickListener listener;

    public MembersViewHolder(@NonNull View itemView, OnCheckBoxClickListener listener) {
        super(itemView);
        this.listener = listener;
        visitedCheckBox = itemView.findViewById(R.id.visitedCheckBox);
        visitedCheckBox.setOnClickListener(this);
        firstNameView = itemView.findViewById(R.id.firstNameText);
        lastNameView = itemView.findViewById(R.id.lastNameText);
        patronymicView = itemView.findViewById(R.id.patronymicText);
        itemView.setOnClickListener(this);
    }

    public void bind(Member model) {
        visitedCheckBox.setChecked(model.isVisited());
        firstNameView.setText(model.getFirstName());
        lastNameView.setText(model.getLastName());
        patronymicView.setText(model.getPatronymic());
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.visitedCheckBox) {
            listener.onCheckBoxClicked(getAdapterPosition(), visitedCheckBox.isChecked());
        } else {
            listener.onItemListClick(getAdapterPosition());
        }

    }
}
