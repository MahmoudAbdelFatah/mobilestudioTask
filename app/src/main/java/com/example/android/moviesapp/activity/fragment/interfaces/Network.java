package com.example.android.moviesapp.activity.fragment.interfaces;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network implements INetwork {
    Context context;

    public Network(Context context) {
        this.context = context;
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
