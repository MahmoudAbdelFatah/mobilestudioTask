package com.example.android.moviesapp.activity.fragment.interfaces;

import com.example.android.moviesapp.interfaces.ICallBackJsonItems;
import com.example.android.moviesapp.interfaces.ICallBackJsonObject;
import com.example.android.moviesapp.model.Trailer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JsonTrailerParser extends JsonManager implements ICallBackJsonObject {
    List<Trailer> trailers;

    public JsonTrailerParser() {
        trailers = new ArrayList<>();
    }

    private List<Trailer> getTrailers() {
        return trailers;
    }

    private void editJson(JsonObject result) {
        Trailer trailer;
        String tmp;
        JsonArray jsonArray = result.getAsJsonArray("results");
        for (int i = 0; i < jsonArray.size(); i++) {
            trailer = new Trailer();

            //key of trailer
            tmp = jsonArray.get(i).getAsJsonObject().get("key").toString();
            tmp = tmp.substring(1, tmp.length() - 1);
            trailer.setKey(tmp);

            //name of trailer
            tmp = jsonArray.get(i).getAsJsonObject().get("name").toString();
            tmp = tmp.substring(1, tmp.length() - 1);
            trailer.setName(tmp);
            trailers.add(trailer);

        }
    }

    @Override
    public void setJsonObject(JsonObject jsonObject, ICallBackJsonItems jsonItems) {
        editJson(jsonObject);
        jsonItems.setData(getTrailers());
    }

}
