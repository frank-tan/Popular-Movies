package com.franktan.popularmovies.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import com.franktan.popularmovies.R;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * A helper class with a bunch of static methods
 * Created by tan on 23/08/2015.
 */
public class Utilities {

    /**
     * Get the API key for movieDB sync
     * @param context
     * @return
     */
    public static String getMovieDBApiKey(Context context) {
        return context.getString(R.string.moviedb_api_key);
    }

    /**
     * Get the API key for retrieving youtube thumbnails
     * @param context
     * @return
     */
    public static String getGoogleApiKey(Context context) {
        return context.getString(R.string.google_api_key);
    }

    /**
     * Convert DP to pixels for the current device
     * @param context
     * @param dp
     * @return
     */
    public static int pixelSizeFromDp(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * dp;
        return (int) (fpixels + 0.5f);
    }

    /**
     * Returns the epoch time in miliseconds
     * @return
     */
    public static Long getCurrentTimeInMillis() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        return cal.getTimeInMillis();
    }

    /**
     * Check if network is available
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable (Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
