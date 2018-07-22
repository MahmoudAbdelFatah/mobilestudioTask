package com.example.android.moviesapp.activity.fragment.view.interfaces;

import android.content.Context;

public interface IMainView {
    void displayToast(String textToast);
    int numberOfColumns();
    void updateOrderType();
}
