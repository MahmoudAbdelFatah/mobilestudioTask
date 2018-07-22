package com.example.android.moviesapp.activity.fragment.home.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.example.android.moviesapp.model.DataItem;
import com.example.android.moviesapp.model.Uris;
import com.example.android.moviesapp.interfaces.IMovieChosen;

import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements IMainView {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SharedPreferences sharedPref;
    public static ArrayList<DataItem>lstDataItems;
    private Toast mToast;
    private IMainPresenter mainPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        mainPresenter = new MainPresenter(getActivity());

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

    @Override
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
        updateOrderType();
    }

    @Override
    public void updateOrderType(){
        lstDataItems.clear();
        String query="";
        URL url ;
        try{
            sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
            String orderType = sharedPref.getString("movie", "popular");
            switch (orderType){
                case "top_rated":
                    query= Uris.MOVIE_BASE_URL+Uris.TOP_RATED+"?api_key="+Uris.API_KEY;
                    if(mainPresenter.isNetworkAvailable()) {
                        ArrayList<DataItem> dataItems = mainPresenter.downloadFromInternet(query);
                        for (DataItem dataItem: dataItems) {
                            lstDataItems.add(dataItem)  ;
                        }

                        movieAdapter.notifyDataSetChanged();
                    }
                    else{
                        displayToast("No internet connection");
                    }
                    break;
                case "popular":
                    query =Uris.MOVIE_BASE_URL+Uris.POPULAR+"?api_key="+Uris.API_KEY;
                    if(mainPresenter.isNetworkAvailable()) {
                        lstDataItems = mainPresenter.downloadFromInternet(query);
                        movieAdapter.notifyDataSetChanged();
                    }
                    else{
                        displayToast("No internet connection");
                    }
                    break;
                case "favorite_movie":
                    mainPresenter.getFavoriteMoviesFromDb();
                    if(lstDataItems.size()>0){
                        movieAdapter.notifyDataSetChanged();
                    }else{
                        displayToast("There isn't Favorite Movies");
                        movieAdapter.notifyDataSetChanged();
                    }
                    break;
            }

        }catch (Exception e){
            Log.e("connect", "the internet not working");
        }
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
}
