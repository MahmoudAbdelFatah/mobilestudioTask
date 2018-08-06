package com.example.android.moviesapp.activity.fragment.home.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.moviesapp.R;
import com.example.android.moviesapp.activity.fragment.home.presenter.IMainPresenter;
import com.example.android.moviesapp.activity.fragment.home.presenter.MainPresenter;
import com.example.android.moviesapp.activity.fragment.interfaces.IMainView;
import com.example.android.moviesapp.adapters.MovieAdapter;
import com.example.android.moviesapp.interfaces.IMovieChosen;
import com.example.android.moviesapp.model.DataItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements IMainView {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    public static ArrayList<DataItem>lstDataItems;
    private Toast mToast;
    private IMainPresenter mainPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        lstDataItems = new ArrayList<>();

        movieAdapter = new MovieAdapter(getActivity(), lstDataItems);
        movieAdapter.setMovieChosen((IMovieChosen) getActivity());

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns()));
        recyclerView.setAdapter(movieAdapter);

        return rootView;
    }

    public int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView.setAdapter(movieAdapter);
        mainPresenter = new MainPresenter(this);
        showMovies();
    }

    @Override
    public void setMovies(List<DataItem> dataItems) {
        lstDataItems.addAll(dataItems);
        movieAdapter.notifyDataSetChanged();
        if (lstDataItems.size() == 0) {
            displayToast("No Movies To Display");
        }
    }

    private void showMovies() {
        lstDataItems.clear();
        mainPresenter.getData();
    }

    @Override
    public void displayToast(String toastText) {
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(getContext(), toastText, Toast.LENGTH_LONG);
        mToast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }
}
