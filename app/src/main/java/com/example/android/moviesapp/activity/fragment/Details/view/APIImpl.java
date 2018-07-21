package com.example.android.moviesapp.activity.fragment.Details.view;

import android.content.Context;
import android.util.Log;

import com.example.android.moviesapp.model.DataItem;
import com.example.android.moviesapp.model.Review;
import com.example.android.moviesapp.model.Trailer;
import com.example.android.moviesapp.model.Uris;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class APIImpl implements IAPI {
    Trailer mTrailers;
    Review mReview;

    Context context;
    public APIImpl(Context context) {
        this.context = context;
    }


    @Override
    public ArrayList<Trailer> getTrailers(DataItem dataItem, final ArrayList<Trailer> lstTrailers) {
        lstTrailers.clear();
        final Integer MOVIE_ID = dataItem.getId();
        String url = "";
        try {
            url = Uris.MOVIE_BASE_URL + MOVIE_ID + Uris.TRAILER + "?api_key=" + Uris.API_KEY;
            //Log.i("URI", url);
            Ion.with(context)
                    .load(url)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        String tmp = "";

                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            if (e == null) {
                                JsonArray jsonArray = result.getAsJsonArray("results");
                                for (int i = 0; i < jsonArray.size(); i++) {
                                mTrailers = new Trailer();

                                //key of trailer
                                tmp = jsonArray.get(i).getAsJsonObject().get("key").toString();
                                tmp = tmp.substring(1, tmp.length() - 1);
                                mTrailers.setKey(tmp);

                                //name of trailer
                                tmp = jsonArray.get(i).getAsJsonObject().get("name").toString();
                                tmp = tmp.substring(1, tmp.length() - 1);
                                mTrailers.setName(tmp);
                                lstTrailers.add(mTrailers);

                                }
                            }
                            Log.i("test", "" + lstTrailers.size());
                        }
                    });
        } catch (Exception e) {
            Log.v("connect", "Ion not work well!");
        }
        return lstTrailers;
    }

    @Override
    public ArrayList<Review> getReviews(DataItem dataItem , final ArrayList<Review> lstReview) {
        lstReview.clear();
        final Integer MOVIE_ID = dataItem.getId();
        String url = "";
        try {
            url = Uris.MOVIE_BASE_URL + MOVIE_ID + Uris.REVIEWS + "?api_key=" + Uris.API_KEY;
            Log.i("URI", url);
            Ion.with(context)
                    .load(url)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        String tmp = "";

                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            if (e == null) {
                                JsonArray jsonArray = result.getAsJsonArray("results");
                                for (int i = 0; i < jsonArray.size(); i++) {
                                    mReview = new Review();

                                    //id of review
                                    tmp = jsonArray.get(i).getAsJsonObject().get("id").toString();
                                    tmp = tmp.substring(1, tmp.length() - 1);
                                    mReview.setId(tmp);

                                    //author of review
                                    tmp = jsonArray.get(i).getAsJsonObject().get("author").toString();
                                    tmp = tmp.substring(1, tmp.length() - 1);
                                    mReview.setAuthor(tmp);

                                    //content of review
                                    tmp = jsonArray.get(i).getAsJsonObject().get("content").toString();
                                    tmp = tmp.substring(1, tmp.length() - 1);
                                    mReview.setContent(tmp);

                                    lstReview.add(mReview);

                                }
                            }
                            Log.i("test", "" + lstReview.size());
                            //reviewAdapter.notifyDataSetChanged();
                        }
                    });
        } catch (Exception e) {
            Log.v("connect", "Ion not work well!");
        }
        return lstReview;
    }
}

