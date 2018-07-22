package com.example.android.moviesapp.activity.fragment.presenter;

import android.content.Context;

import com.example.android.moviesapp.model.DataItem;

import java.util.ArrayList;

public interface IMainPresenter {
    void getFavoriteMoviesFromDb();
    ArrayList<DataItem> downloadFromInternet(String url);
    boolean isNetworkAvailable();
}
