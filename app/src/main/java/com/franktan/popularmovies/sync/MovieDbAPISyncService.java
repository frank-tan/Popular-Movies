package com.franktan.popularmovies.sync;

import android.content.Context;

import com.franktan.popularmovies.R;

/**
 * Created by tan on 16/08/2015.
 */
public class MovieDbAPISyncService {
    public static String getApiKey(Context context) {
        return context.getString(R.string.moviedb_api_key);
    }

    public String getMovieInfoFromAPI(String sortby, long releaseDateFrom) {
        return null;
    }

    public static MovieDbAPISyncService getDbSyncService() {
        return null;
    }
}
