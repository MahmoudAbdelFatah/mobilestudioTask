package com.example.android.moviesapp.activity.fragment.detail.presenter;


import com.example.android.moviesapp.activity.fragment.interfaces.IDetailView;
import com.example.android.moviesapp.activity.fragment.interfaces.IRepository;
import com.example.android.moviesapp.activity.fragment.interfaces.IonRestfullAPI;
import com.example.android.moviesapp.activity.fragment.interfaces.IonRestfullAPIContract;
import com.example.android.moviesapp.activity.fragment.interfaces.JsonReviewParser;
import com.example.android.moviesapp.activity.fragment.interfaces.JsonTrailerParser;
import com.example.android.moviesapp.activity.fragment.interfaces.MovieRepository;
import com.example.android.moviesapp.interfaces.ICallBackJsonItems;
import com.example.android.moviesapp.model.DataItem;
import com.example.android.moviesapp.model.Review;
import com.example.android.moviesapp.model.Trailer;
import com.example.android.moviesapp.model.Uris;

import java.util.List;

public class DetailPresenter implements IDetailPresenter, ICallBackJsonItems {
    IRepository repository;
    IonRestfullAPIContract api;
    IDetailView detailView;
    boolean flag = false;

    public DetailPresenter(IDetailView detailView) {
        this.detailView = detailView;
        repository = new MovieRepository(detailView.getContext());
        api = new IonRestfullAPI();
    }


    @Override
    public void getData(DataItem dataItem) {
        String url;
        final Integer MOVIE_ID = dataItem.getId();

        //for trailers
        url = Uris.MOVIE_BASE_URL + MOVIE_ID + Uris.TRAILER + "?api_key=" + Uris.API_KEY;
        api.loadData(detailView.getContext(), url, new JsonTrailerParser(), this);
        //for reviews
        url = Uris.MOVIE_BASE_URL + MOVIE_ID + Uris.REVIEWS + "?api_key=" + Uris.API_KEY;
        api.loadData(detailView.getContext(), url, new JsonReviewParser(), this);
        flag = true;
    }

    @Override
    public <T> void setData(List<T> data) {
        detailView.setTrailers((List<Trailer>) data);
        if (true)
            detailView.setReviews((List<Review>) data);
    }
}
