package com.example.android.moviesapp.activity.fragment.interfaces;

import android.database.Cursor;

import com.example.android.moviesapp.model.DataItem;

public interface IRepository {
    boolean flagFav(DataItem dataItem);
    boolean addMovieDb(DataItem dataItem);
    boolean deleteMovieDb(DataItem dataItem);

    void getFavoriteMoviesFromDb();
    Cursor getMoviesEntry();
    void moviesFromDb(Cursor cursor);




}
