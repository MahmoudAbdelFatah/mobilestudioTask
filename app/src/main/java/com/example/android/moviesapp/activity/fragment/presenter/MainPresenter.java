package com.example.android.moviesapp.activity.fragment.presenter;

import android.content.Context;

import com.example.android.moviesapp.activity.fragment.view.interfaces.APIImpl;
import com.example.android.moviesapp.activity.fragment.view.interfaces.IAPI;
import com.example.android.moviesapp.activity.fragment.view.interfaces.INetwork;
import com.example.android.moviesapp.activity.fragment.view.interfaces.IRepository;
import com.example.android.moviesapp.activity.fragment.view.interfaces.NetworkImpl;
import com.example.android.moviesapp.activity.fragment.view.interfaces.RepositoryImpl;
import com.example.android.moviesapp.model.DataItem;

import java.util.ArrayList;

public class MainPresenter implements IMainPresenter {
    Context context;
    IRepository repository;
    IAPI api;
    INetwork network;

    public MainPresenter(Context context) {
        this.context = context;
        repository = new RepositoryImpl(context);
        api = new APIImpl(context);
        network = new NetworkImpl(context);
    }

    @Override
    public void getFavoriteMoviesFromDb() {
        repository.getFavoriteMoviesFromDb();
    }

    @Override
    public ArrayList<DataItem> downloadFromInternet(String url) {
        return api.downloadFromInternet(url);
    }

    @Override
    public boolean isNetworkAvailable() {
        return network.isNetworkAvailable();
    }


}
