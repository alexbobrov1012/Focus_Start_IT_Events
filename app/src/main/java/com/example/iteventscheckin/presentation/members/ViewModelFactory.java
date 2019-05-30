package com.example.iteventscheckin.presentation.members;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private int arg;

    public ViewModelFactory(int arg) {
        this.arg = arg;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new MembersViewModel(arg);
    }
}
