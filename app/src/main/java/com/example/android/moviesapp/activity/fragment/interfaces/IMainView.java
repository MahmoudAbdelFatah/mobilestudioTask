package com.example.android.moviesapp.activity.fragment.interfaces;

import android.content.Context;

import com.example.android.moviesapp.model.DataItem;

import java.util.List;

public interface IMainView {
    void displayToast(String textToast);

    Context getContext();

    void setMovies(List<DataItem> dataItems);
}
