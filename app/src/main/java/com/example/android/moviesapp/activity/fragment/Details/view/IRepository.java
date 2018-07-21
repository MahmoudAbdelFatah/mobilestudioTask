package com.example.android.moviesapp.activity.fragment.Details.view;

import com.example.android.moviesapp.model.DataItem;

public interface IRepository {
    boolean flagFav(DataItem dataItem);
    boolean addMovieDb(DataItem dataItem);
    boolean deleteMovieDb(DataItem dataItem);




}
