package com.example.android.moviesapp.activity.fragment.view.interfaces;

import android.content.Context;
import android.util.Log;

import com.example.android.moviesapp.activity.fragment.view.fragment.MainActivityFragment;
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

    @Override
    public ArrayList<DataItem> downloadFromInternet(String url) {
        final ArrayList<DataItem> lstDataItems = new ArrayList<>();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    DataItem dataItem;
                    String imageUrl;
                    String tmp;

                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e == null) {
                            JsonArray jsonArray = result.getAsJsonArray("results");
                            for (int i = 0; i < jsonArray.size(); i++) {
                                dataItem = new DataItem();

                                //Image Poster
                                imageUrl = jsonArray.get(i).getAsJsonObject().get("poster_path").toString();
                                imageUrl = imageUrl.substring(2, imageUrl.length() - 1);
                                imageUrl = Uris.IMAGE_PATH + imageUrl;
                                dataItem.imageUrl = imageUrl;

                                //overView
                                tmp = jsonArray.get(i).getAsJsonObject().get("overview").toString();
                                tmp = tmp.substring(1, tmp.length() - 1);
                                dataItem.overview = tmp;

                                //original title for movie
                                tmp = jsonArray.get(i).getAsJsonObject().get("original_title").toString();
                                tmp = tmp.substring(1, tmp.length() - 1);
                                dataItem.original_title = tmp;

                                //vote average
                                tmp = jsonArray.get(i).getAsJsonObject().get("vote_average").toString();
                                tmp = tmp + "/10.0";
                                dataItem.vote_average = tmp;

                                //release Date
                                tmp = jsonArray.get(i).getAsJsonObject().get("release_date").toString();
                                tmp = tmp.substring(1, tmp.length() - 1);
                                dataItem.release_date = tmp;

                                imageUrl = jsonArray.get(i).getAsJsonObject().get("backdrop_path").toString();
                                imageUrl = imageUrl.substring(2, imageUrl.length() - 1);
                                imageUrl = Uris.IMAGE_PATH + imageUrl;
                                dataItem.backdrop_path = imageUrl;

                                dataItem.id = Integer.parseInt(jsonArray.get(i).getAsJsonObject().get("id").toString());

                                Log.i("test", "in ION");
                                //movieAdapter.add(dataItem);
                                lstDataItems.add(dataItem);
                            }
                        }
                    }
                });
        return lstDataItems;
    }
}

