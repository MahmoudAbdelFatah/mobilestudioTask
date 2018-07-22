package com.example.android.moviesapp.activity.fragment.interfaces;

import android.view.View;

public interface IDetailView {
    void displayToast(String textToast);
    void injectViews(View rootView);
    void init();
    void setAdapters();

}
