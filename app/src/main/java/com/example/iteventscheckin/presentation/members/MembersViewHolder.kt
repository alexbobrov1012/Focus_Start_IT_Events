package com.example.iteventscheckin.presentation.members

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iteventscheckin.R
import com.example.iteventscheckin.models.Member
import com.example.iteventscheckin.presentation.OnCheckBoxClickListener
import com.example.iteventscheckin.presentation.OnItemListClickListener

class MembersViewHolder(itemView: View, private val listener: OnCheckBoxClickListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val firstNameView: TextView

    private val lastNameView: TextView

    private val patronymicView: TextView

    private val visitedCheckBox: CheckBox

    init {
        visitedCheckBox = itemView.findViewById(R.id.visitedCheckBox)
        visitedCheckBox.setOnClickListener(this)
        firstNameView = itemView.findViewById(R.id.firstNameText)
        lastNameView = itemView.findViewById(R.id.lastNameText)
        patronymicView = itemView.findViewById(R.id.patronymicText)
        itemView.setOnClickListener(this)
    }

    fun bind(model: Member) {
        visitedCheckBox.isChecked = model.isVisited
        firstNameView.text = model.firstName
        lastNameView.text = model.lastName
        patronymicView.text = model.patronymic
    }


    override fun onClick(v: View) {
        if (v.id == R.id.visitedCheckBox) {
            listener.onCheckBoxClicked(adapterPosition, visitedCheckBox.isChecked)
        } else {
            listener.onItemListClick(adapterPosition)
        }

    }
}
