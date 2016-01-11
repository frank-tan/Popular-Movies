package com.franktan.popularmovies;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by tan on 17/09/2015.
 */
public class PopularMovieApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //LeakCanary.install(this);

        // config Picasso to use OkHttp for image caching
        // This will speed up image loading time and allow offline usage of the app
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        if(BuildConfig.DEBUG) {
            built.setLoggingEnabled(true);
        }
        Picasso.setSingletonInstance(built);
    }
}
