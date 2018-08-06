package com.example.android.moviesapp.activity.fragment.home.presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.android.moviesapp.activity.fragment.interfaces.IMainView;
import com.example.android.moviesapp.activity.fragment.interfaces.INetwork;
import com.example.android.moviesapp.activity.fragment.interfaces.IRepository;
import com.example.android.moviesapp.activity.fragment.interfaces.IonRestfullAPI;
import com.example.android.moviesapp.activity.fragment.interfaces.IonRestfullAPIContract;
import com.example.android.moviesapp.activity.fragment.interfaces.JsonMovieParser;
import com.example.android.moviesapp.activity.fragment.interfaces.MovieRepository;
import com.example.android.moviesapp.activity.fragment.interfaces.Network;
import com.example.android.moviesapp.interfaces.ICallBackJsonItems;
import com.example.android.moviesapp.model.DataItem;
import com.example.android.moviesapp.model.Uris;

import java.util.List;

public class MainPresenter implements IMainPresenter, ICallBackJsonItems {
    IMainView mainView;
    IRepository repository;
    IonRestfullAPIContract api;
    INetwork network;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        repository = new MovieRepository(mainView.getContext());
        api = new IonRestfullAPI();
        network = new Network(mainView.getContext());
    }

    @Override
    public void getData() {
        String query = "";
        try {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mainView.getContext());
            String orderType = sharedPref.getString("movie", "popular");
            switch (orderType) {
                case "top_rated":
                    query = Uris.MOVIE_BASE_URL + Uris.TOP_RATED + "?api_key=" + Uris.API_KEY;
                    callApi(query);
                    break;
                case "popular":
                    query = Uris.MOVIE_BASE_URL + Uris.POPULAR + "?api_key=" + Uris.API_KEY;
                    callApi(query);
                    break;
                case "favorite_movie":
                    repository.getFavoriteMovies();
                    break;
            }

        } catch (Exception e) {
            Log.e("connect", "the internet not working");
        }
    }


    private void callApi(String query) {
        if (network.isNetworkAvailable()) {
            api.loadData(mainView.getContext(), query, new JsonMovieParser(), this);
        } else {
            mainView.displayToast("No Internet");
        }
    }


    @Override
    public <T> void setData(List<T> data) {
        mainView.setMovies((List<DataItem>) data);
    }
}
