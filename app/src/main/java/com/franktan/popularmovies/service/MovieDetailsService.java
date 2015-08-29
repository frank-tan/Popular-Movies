package com.franktan.popularmovies.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by tan on 29/08/2015.
 */
public class MovieDetailsService extends IntentService {
    public MovieDetailsService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
