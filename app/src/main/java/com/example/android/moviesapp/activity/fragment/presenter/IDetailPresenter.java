package com.example.android.moviesapp.activity.fragment.presenter;


import com.example.android.moviesapp.model.DataItem;
import com.example.android.moviesapp.model.Review;
import com.example.android.moviesapp.model.Trailer;

import java.util.ArrayList;

public interface IDetailPresenter {
    void addMovie(DataItem dataItem);

    void deleteMovie(DataItem dataItem);

    boolean flagFav(DataItem dataItem);

    ArrayList<Trailer> getTrailers(DataItem dataItem, ArrayList<Trailer> lstTrailers);

    ArrayList<Review> getReviews(DataItem dataItem, ArrayList<Review> lstReview);
}
