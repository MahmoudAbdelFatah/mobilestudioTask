package com.example.android.moviesapp.activity.fragment.interfaces;

import com.example.android.moviesapp.model.DataItem;

public interface IRepository {
    boolean flagFav(DataItem dataItem);

    boolean addMovie(DataItem dataItem);

    boolean deleteMovie(DataItem dataItem);

    void getFavoriteMovies();
}
