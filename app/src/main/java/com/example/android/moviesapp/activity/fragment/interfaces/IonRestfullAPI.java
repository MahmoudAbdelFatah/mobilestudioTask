package com.example.android.moviesapp.activity.fragment.interfaces;

import android.content.Context;

import com.example.android.moviesapp.interfaces.ICallBackJsonItems;

public class IonRestfullAPI implements IonRestfullAPIContract {

    public IonRestfullAPI() {
    }

    @Override
    public void loadData(Context context, String url, IJsonManager jsonManager, ICallBackJsonItems jsonItems) {
        jsonManager.getJson(context, url, jsonManager, jsonItems);
    }

}

