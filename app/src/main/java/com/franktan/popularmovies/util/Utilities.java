package com.franktan.popularmovies.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    public static String getApiKey(Context context) {
        return context.getString(R.string.moviedb_api_key);
    }
}
