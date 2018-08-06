package com.example.android.moviesapp.activity.fragment.interfaces;

import android.content.Context;

import com.example.android.moviesapp.model.Review;
import com.example.android.moviesapp.model.Trailer;

import java.util.List;

public interface IDetailView {
    void displayToast(String textToast);

    Context getContext();

    void setTrailers(List<Trailer> trailers);

    void setReviews(List<Review> reviews);
}
