package com.example.android.moviesapp.activity.fragment.detail.presenter;

import com.example.android.moviesapp.activity.fragment.interfaces.IDetailView;
import com.example.android.moviesapp.activity.fragment.interfaces.IRepository;
import com.example.android.moviesapp.activity.fragment.interfaces.MovieRepository;
import com.example.android.moviesapp.model.DataItem;

public class DetailRepository implements IDetailRepository {
    IRepository repository;
    IDetailView detailView;

    public DetailRepository(IDetailView detailView) {
        this.detailView = detailView;
        repository = new MovieRepository(detailView.getContext());
    }

    @Override
    public void addMovie(DataItem dataItem) {
        if (repository.addMovie(dataItem)) {
            detailView.displayToast("Movie Added to Favorite Movies!");
        }
    }

    @Override
    public void deleteMovie(DataItem dataItem) {
        if (repository.deleteMovie(dataItem)) {
            detailView.displayToast("Movie Deleted from Favorite Movies!");
        }
    }

    @Override
    public boolean flagFav(DataItem dataItem) {
        return repository.flagFav(dataItem);
    }
}
