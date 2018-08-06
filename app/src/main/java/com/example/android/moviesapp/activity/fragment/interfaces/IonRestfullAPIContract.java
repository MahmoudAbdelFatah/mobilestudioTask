package com.example.android.moviesapp.activity.fragment.interfaces;

import android.content.Context;

import com.example.android.moviesapp.interfaces.ICallBackJsonItems;

public interface IonRestfullAPIContract {
    void loadData(Context context, String url, IJsonManager jsonManager, ICallBackJsonItems jsonItems);
}
