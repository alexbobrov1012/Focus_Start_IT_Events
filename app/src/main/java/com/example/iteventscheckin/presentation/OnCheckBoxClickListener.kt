package com.example.iteventscheckin.presentation

interface OnCheckBoxClickListener : OnItemListClickListener {
    fun onCheckBoxClicked(adapterPosition: Int, isChecked: Boolean)
}
