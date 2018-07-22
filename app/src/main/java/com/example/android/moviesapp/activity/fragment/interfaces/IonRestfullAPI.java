package com.example.android.moviesapp.activity.fragment.interfaces;

import com.example.android.moviesapp.model.DataItem;
import com.example.android.moviesapp.model.Review;
import com.example.android.moviesapp.model.Trailer;

import java.util.ArrayList;

public interface IonRestfullAPI {
    ArrayList<Trailer> getTrailers(DataItem dataItem, ArrayList<Trailer> lstTrailers);
    ArrayList<Review> getReviews(DataItem dataItem, ArrayList<Review> lstReview);
    ArrayList<DataItem> downloadFromInternet(String url);

}
