package com.example.android.moviesapp.activity.fragment.interfaces;

import android.content.Context;

import com.example.android.moviesapp.interfaces.ICallBackJsonItems;
import com.example.android.moviesapp.interfaces.ICallBackJsonObject;

public interface IJsonManager extends ICallBackJsonObject {
    void getJson(Context context, String query, ICallBackJsonObject callBackJsonObject, ICallBackJsonItems jsonItems);
}
