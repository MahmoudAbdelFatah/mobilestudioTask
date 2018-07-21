package com.example.android.moviesapp.activity.fragment.Details.view;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.android.moviesapp.database.FavoriteMoviesContract;
import com.example.android.moviesapp.model.DataItem;


public class RepositoryImpl implements IRepository {
    Context context;

    public RepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean flagFav(DataItem dataItem) {
        Cursor cursor = context.getContentResolver().query(
                FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI,
                new String[]{FavoriteMoviesContract.FavoriteMoviesEntry.MOVIE_ID},
                FavoriteMoviesContract.FavoriteMoviesEntry.MOVIE_ID + " = ? ",
                new String[]{dataItem.getId().toString()},
                null
        );
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        // if Database contains movie
        //isFav = true;
        cursor.close();
        return true;
    }

    @Override
    public boolean addMovieDb(DataItem dataItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoriteMoviesContract.FavoriteMoviesEntry.MOVIE_ID, dataItem.getId());
        contentValues.put(FavoriteMoviesContract.FavoriteMoviesEntry.MOVIE_ORIGINAL_TITLE, dataItem.getOriginal_title());
        contentValues.put(FavoriteMoviesContract.FavoriteMoviesEntry.MOVIE_OVERVIEW, dataItem.getOverview());
        contentValues.put(FavoriteMoviesContract.FavoriteMoviesEntry.MOVIE_IMAGE_URL, dataItem.getImageUrl());
        contentValues.put(FavoriteMoviesContract.FavoriteMoviesEntry.MOVIE_VOTE_AVERAGE, dataItem.getVote_average());
        contentValues.put(FavoriteMoviesContract.FavoriteMoviesEntry.MOVIE_RELEASE_DATE, dataItem.getRelease_date());
        contentValues.put(FavoriteMoviesContract.FavoriteMoviesEntry.MOVIE_BACKDROP_PATH, dataItem.getBackdrop_path());

        Uri insertUri = context.getContentResolver().insert(FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI,
                contentValues);
        long insertedId = ContentUris.parseId(insertUri);
        if (insertedId > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMovieDb(DataItem dataItem) {
        int rowDeleted = context.getContentResolver().delete(
                FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI,
                FavoriteMoviesContract.FavoriteMoviesEntry.MOVIE_ID + " = ?",
                new String[]{dataItem.getId().toString()}
        );
        if (rowDeleted > 0) {
            return true;
        }
        return false;
    }
}
