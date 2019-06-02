package com.example.iteventscheckin.presentation;

public interface OnCheckBoxClickListener extends OnItemListClickListener{
    void onCheckBoxClicked(int adapterPosition, boolean isChecked);
}
