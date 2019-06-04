package com.example.iteventscheckin.presentation.members

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iteventscheckin.R
import com.example.iteventscheckin.models.Member
import com.example.iteventscheckin.presentation.OnCheckBoxClickListener
import com.example.iteventscheckin.presentation.OnItemListClickListener

class MembersAdapter internal constructor(private val listener: OnCheckBoxClickListener) :
    RecyclerView.Adapter<MembersViewHolder>() {

    private var members: List<Member>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.members_item, parent, false)
        return MembersViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) {
        holder.bind(members!![position])
    }

    override fun getItemCount(): Int {
        return if (members == null) 0 else members!!.size
    }

    fun setMembers(members: List<Member>) {
        this.members = members
        notifyDataSetChanged()
    }

    fun getMemberItem(position: Int): Member {
        return members!![position]
    }

    fun getMemberid(position: Int): Int {
        return members!![position].id
    }

}
