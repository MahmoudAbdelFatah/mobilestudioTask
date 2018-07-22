package com.example.android.moviesapp.activity.fragment.presenter;


import android.content.Context;

import com.example.android.moviesapp.activity.fragment.view.interfaces.APIImpl;
import com.example.android.moviesapp.activity.fragment.view.fragment.DetailActivityFragment;
import com.example.android.moviesapp.activity.fragment.view.interfaces.IAPI;
import com.example.android.moviesapp.activity.fragment.view.interfaces.IRepository;
import com.example.android.moviesapp.activity.fragment.view.interfaces.RepositoryImpl;
import com.example.android.moviesapp.activity.fragment.view.interfaces.IDetailView;
import com.example.android.moviesapp.model.DataItem;
import com.example.android.moviesapp.model.Review;
import com.example.android.moviesapp.model.Trailer;

import java.util.ArrayList;

public class DetailPresenterImpl implements IDetailPresenter {
    Context context;
    IRepository repository;
    IDetailView detailView;
    IAPI api;


    public DetailPresenterImpl(Context context) {
        this.context = context;
        repository = new RepositoryImpl(context);
        detailView = new DetailActivityFragment();
        api = new APIImpl(context);
    }


    @Override
    public void addMovie(DataItem dataItem) {
        if(repository.addMovieDb(dataItem)) {
            detailView.displayToast("Movie Added to Favorite Movies!");
        }
    }

    @Override
    public void deleteMovie(DataItem dataItem) {
        if(repository.deleteMovieDb(dataItem)) {
            detailView.displayToast("Movie Added to Favorite Movies!");
        }
    }

    @Override
    public boolean flagFav(DataItem dataItem) {
        return repository.flagFav(dataItem);
    }

    @Override
    public ArrayList<Trailer> getTrailers(DataItem dataItem, ArrayList<Trailer> lstTrailers) {
        ArrayList<Trailer> trailers = api.getTrailers(dataItem, lstTrailers);
        return trailers;
    }

    @Override
    public ArrayList<Review> getReviews(DataItem dataItem, ArrayList<Review> lstReview) {
        ArrayList<Review> reviews = api.getReviews(dataItem, lstReview);
        return reviews;
    }

}
