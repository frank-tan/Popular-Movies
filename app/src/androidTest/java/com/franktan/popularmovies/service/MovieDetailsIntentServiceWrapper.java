package com.franktan.popularmovies.service;

import android.content.Intent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tan on 1/09/2015.
 */
public class MovieDetailsIntentServiceWrapper extends MovieDetailsIntentService {
    private CountDownLatch latch;

    public MovieDetailsIntentServiceWrapper () {
        super("MovieDetailsIntentServiceWrapper");
    }

    public MovieDetailsIntentServiceWrapper (String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        super.onHandleIntent(intent);
        latch.countDown();
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}
