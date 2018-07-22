package com.example.android.moviesapp.activity.fragment.view.interfaces;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

import com.example.android.moviesapp.activity.fragment.view.fragment.MainActivityFragment;
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

    @Override
    public void getFavoriteMoviesFromDb() {
        Cursor cursor = getMoviesEntry();
        if (cursor.moveToFirst()) {
            moviesFromDb(cursor);
        }
        cursor.close();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Cursor getMoviesEntry() {
        return context.getContentResolver().query(
                FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void moviesFromDb(Cursor cursor) {
        DataItem dataItem ;
        do {
            dataItem = new DataItem();
            //Read row by row
            dataItem.id = Integer.parseInt(cursor.getString(0));
            dataItem.original_title = cursor.getString(1);
            dataItem.overview = cursor.getString(2);
            dataItem.imageUrl = cursor.getString(3);
            dataItem.vote_average = cursor.getString(4);
            dataItem.release_date = cursor.getString(5);
            dataItem.backdrop_path = cursor.getString(6);
            MainActivityFragment.lstDataItems.add(dataItem);
        } while (cursor.moveToNext());
    }
}
