package com.franktan.popularmovies.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import com.franktan.popularmovies.R;

/**
 * Created by tan on 23/08/2015.
 */
public class Utilities {

    public static String getSortOrderPreference (Context context) {
        String sortPrefKey = context.getString(R.string.sort_order_key);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(sortPrefKey, "popularity");
    }

    public static String getMovieDBApiKey(Context context) {
        return context.getString(R.string.moviedb_api_key);
    }

    public static String getGoogleApiKey(Context context) {
        return context.getString(R.string.google_api_key);
    }

    public static int pixelSizeFromDp(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * dp;
        return (int) (fpixels + 0.5f);
    }
}
