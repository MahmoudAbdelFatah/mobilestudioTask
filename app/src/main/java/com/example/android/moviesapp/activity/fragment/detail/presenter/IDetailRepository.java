package com.example.android.moviesapp.activity.fragment.detail.presenter;

import com.example.android.moviesapp.model.DataItem;

public interface IDetailRepository {
    void addMovie(DataItem dataItem);

    void deleteMovie(DataItem dataItem);

    boolean flagFav(DataItem dataItem);
}
