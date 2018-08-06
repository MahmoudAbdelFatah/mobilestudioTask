package com.example.android.moviesapp.activity.fragment.interfaces;

import android.util.Log;

import com.example.android.moviesapp.interfaces.ICallBackJsonItems;
import com.example.android.moviesapp.interfaces.ICallBackJsonObject;
import com.example.android.moviesapp.model.DataItem;
import com.example.android.moviesapp.model.Uris;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JsonMovieParser extends JsonManager implements ICallBackJsonObject {
    List<DataItem> dataItems;

    public JsonMovieParser() {
        dataItems = new ArrayList<>();
    }

    private void editJson(JsonObject result) {
        DataItem dataItem;
        String imageUrl;
        String tmp;
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
            dataItems.add(dataItem);
        }
    }

    private List<DataItem> getMovies() {
        return dataItems;
    }

    @Override
    public void setJsonObject(JsonObject jsonObject, ICallBackJsonItems jsonItems) {
        editJson(jsonObject);
        jsonItems.setData(getMovies());
    }

}
