package com.example.android.moviesapp.activity.fragment.Details.presenter;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.android.moviesapp.activity.fragment.Details.view.APIImpl;
import com.example.android.moviesapp.activity.fragment.Details.view.DetailActivityFragment;
import com.example.android.moviesapp.activity.fragment.Details.view.IAPI;
import com.example.android.moviesapp.activity.fragment.Details.view.IRepository;
import com.example.android.moviesapp.activity.fragment.Details.view.RepositoryImpl;
import com.example.android.moviesapp.activity.fragment.Details.view.IDetailView;
import com.example.android.moviesapp.database.FavoriteMoviesContract;
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
