package com.example.android.moviesapp.activity.fragment.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moviesapp.R;
import com.example.android.moviesapp.activity.fragment.presenter.DetailPresenterImpl;
import com.example.android.moviesapp.activity.fragment.presenter.IDetailPresenter;
import com.example.android.moviesapp.activity.fragment.view.interfaces.IDetailView;
import com.example.android.moviesapp.adapters.ReviewAdapter;
import com.example.android.moviesapp.adapters.TrailerAdapter;
import com.example.android.moviesapp.interfaces.IOnItemClickListener;
import com.example.android.moviesapp.model.DataItem;
import com.example.android.moviesapp.model.Review;
import com.example.android.moviesapp.model.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivityFragment extends Fragment implements IDetailView {
    private ImageView backdropImage, posterImage;
    private TextView movieTitle, releaseDate, movieRating, overView;
    private DataItem dataItem;
    private Button addButton;
    private boolean isFav = false;
    private RecyclerView rvTrailer, rvReview;
    private ReviewAdapter reviewAdapter;
    private TrailerAdapter trailerAdapter;
    private ArrayList<Trailer> lstTrailers;
    private ArrayList<Review> lstReview;
    private Trailer mTrailers;
    private Review mReview;
    private Toast mToast;
    private IDetailPresenter detailPresenter;

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(intent.EXTRA_TEXT)) {
            Integer position = Integer.parseInt(intent.getStringExtra(intent.EXTRA_TEXT));
            dataItem = MainActivityFragment.lstDataItems.get(position);
        }
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        setHasOptionsMenu(true);
        init();
        injectViews(rootView);
        setAdapters();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFav) {
                    isFav = true;
                    addButton.setBackgroundResource(R.drawable.add_to_db);
                    detailPresenter.addMovie(dataItem);
                } else {
                    isFav = false;
                    addButton.setBackgroundResource(R.drawable.remove_from_db);
                    detailPresenter.deleteMovie(dataItem);
                }
            }
        });

        return rootView;
    }

    @Override
    public void displayToast(String textToast) {
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(getContext(), textToast, Toast.LENGTH_LONG);
        mToast.show();
    }

    @Override
    public void init() {
        lstTrailers = new ArrayList<>();
        lstReview = new ArrayList<>();
        detailPresenter = new DetailPresenterImpl(getContext());
        trailerAdapter = new TrailerAdapter(getContext(), detailPresenter.getTrailers(dataItem, lstTrailers));
        reviewAdapter = new ReviewAdapter(getContext(), detailPresenter.getReviews(dataItem, lstReview));
    }

    @Override
    public void injectViews(View rootView) {
        addButton = (Button) rootView.findViewById(R.id.btn_favourite);
        isFav = detailPresenter.flagFav(dataItem);
        if (isFav) {
            addButton.setBackgroundResource(R.drawable.add_to_db);
        }
        backdropImage = (ImageView) rootView.findViewById(R.id.backdrop_image);
        posterImage = (ImageView) rootView.findViewById(R.id.movie_poster);
        movieTitle = (TextView) rootView.findViewById(R.id.movie_title);
        releaseDate = (TextView) rootView.findViewById(R.id.movie_release_date);
        movieRating = (TextView) rootView.findViewById(R.id.movie_rating);
        overView = (TextView) rootView.findViewById(R.id.overView);
        //set title of the activity
        getActivity().setTitle(dataItem.getOriginal_title());

        Picasso.with(getContext())
                .load(dataItem.getBackdrop_path())
                .into(backdropImage);
        Picasso.with(getContext())
                .load(dataItem.getImageUrl())
                .into(posterImage);

        movieTitle.setText(dataItem.getOriginal_title());
        releaseDate.setText(dataItem.getRelease_date());
        movieRating.setText(dataItem.getVote_average());
        overView.setText(dataItem.getOverview());

        rvTrailer = (RecyclerView) rootView.findViewById(R.id.recyclerView_trailer);
        rvReview = (RecyclerView) rootView.findViewById(R.id.recyclerView_review);

        //set layout
        rvTrailer.setLayoutManager(new LinearLayoutManager(getContext()));
        rvReview.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setAdapters() {
        rvTrailer.setAdapter(trailerAdapter);
        rvReview.setAdapter(reviewAdapter);

        rvTrailer.setAdapter(new TrailerAdapter(lstTrailers, new IOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=" +
                        lstTrailers.get(position).getKey());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }));
    }

}
