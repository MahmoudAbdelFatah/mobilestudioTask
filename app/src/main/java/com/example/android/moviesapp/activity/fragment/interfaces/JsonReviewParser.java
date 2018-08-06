package com.example.android.moviesapp.activity.fragment.interfaces;

import com.example.android.moviesapp.interfaces.ICallBackJsonItems;
import com.example.android.moviesapp.interfaces.ICallBackJsonObject;
import com.example.android.moviesapp.model.Review;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JsonReviewParser extends JsonManager implements ICallBackJsonObject {
    List<Review> reviews;

    public JsonReviewParser() {
        reviews = new ArrayList<>();
    }


    private void editJson(JsonObject result) {
        Review review;
        String tmp = "";
        JsonArray jsonArray = result.getAsJsonArray("results");

        for (int i = 0; i < jsonArray.size(); i++) {
            review = new Review();

            //id of review
            tmp = jsonArray.get(i).getAsJsonObject().get("id").toString();
            tmp = tmp.substring(1, tmp.length() - 1);
            review.setId(tmp);

            //author of review
            tmp = jsonArray.get(i).getAsJsonObject().get("author").toString();
            tmp = tmp.substring(1, tmp.length() - 1);
            review.setAuthor(tmp);

            //content of review
            tmp = jsonArray.get(i).getAsJsonObject().get("content").toString();
            tmp = tmp.substring(1, tmp.length() - 1);
            review.setContent(tmp);

            reviews.add(review);

        }
    }

    private List<Review> getReviews() {
        return reviews;
    }


    @Override
    public void setJsonObject(JsonObject jsonObject, ICallBackJsonItems jsonItems) {
        editJson(jsonObject);
        jsonItems.setData(getReviews());
    }
}
