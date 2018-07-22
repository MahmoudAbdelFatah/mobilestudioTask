package com.example.android.moviesapp.activity.fragment.home.presenter;

import android.content.Context;

import com.example.android.moviesapp.activity.fragment.interfaces.IonRestfullAPIImpl;
import com.example.android.moviesapp.activity.fragment.interfaces.IonRestfullAPI;
import com.example.android.moviesapp.activity.fragment.interfaces.INetwork;
import com.example.android.moviesapp.activity.fragment.interfaces.IRepository;
import com.example.android.moviesapp.activity.fragment.interfaces.NetworkImpl;
import com.example.android.moviesapp.activity.fragment.interfaces.RepositoryImpl;
import com.example.android.moviesapp.model.DataItem;

import java.util.ArrayList;

public class MainPresenter implements IMainPresenter {
    Context context;
    IRepository repository;
    IonRestfullAPI api;
    INetwork network;

    public MainPresenter(Context context) {
        this.context = context;
        repository = new RepositoryImpl(context);
        api = new IonRestfullAPIImpl(context);
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
