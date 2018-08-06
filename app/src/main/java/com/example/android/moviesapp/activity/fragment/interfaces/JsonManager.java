package com.example.android.moviesapp.activity.fragment.interfaces;

import android.content.Context;
import android.util.Log;

import com.example.android.moviesapp.interfaces.ICallBackJsonItems;
import com.example.android.moviesapp.interfaces.ICallBackJsonObject;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class JsonManager implements IJsonManager {

    ICallBackJsonObject callBackJsonObject;
    ICallBackJsonItems jsonItems;

    @Override
    public void getJson(Context context, String url, final ICallBackJsonObject callBackJsonObject, final ICallBackJsonItems jsonItems) {
        this.callBackJsonObject = callBackJsonObject;
        this.jsonItems = jsonItems;
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, final JsonObject result) {
                        if (e != null) {
                            Log.i("url", "wrong URL");

                        }
                        callBackJsonObject.setJsonObject(result, jsonItems);
                    }
                });
    }

}
