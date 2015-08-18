package com.franktan.popularmovies.sync;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.franktan.popularmovies.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by tan on 16/08/2015.
 */
public class MovieDbAPISyncService {
    public static String getApiKey(Context context) {
        return context.getString(R.string.moviedb_api_key);
    }

    //TODO: use Enum for sort by parameter
    public String getMovieInfoFromAPI(Context context, String sortBy, long releaseDateFrom) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        Date date = new Date(releaseDateFrom);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formattedDateFrom = format.format(date);
        Log.i("popularmovies",formattedDateFrom);

        final String MOVIEDB_BASE_URL =
                "http://api.themoviedb.org/3/discover/movie?";
        final String API_KEY = "api_key";
        final String SORT_BY = "sort_by";
        final String PAGE = "page";
        final String RELEASE_BEFORE = "primary_release_date.gte";

        try {
            Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                    .appendQueryParameter(API_KEY, MovieDbAPISyncService.getApiKey(context))
                    .appendQueryParameter(SORT_BY, sortBy)
                    .appendQueryParameter(PAGE, "1")
                    .appendQueryParameter(RELEASE_BEFORE, formattedDateFrom)
                    .build();

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            return buffer.toString();

        } catch (IOException e) {
            return null;
        }

    }

    public static MovieDbAPISyncService getDbSyncService() {
        return new MovieDbAPISyncService();
    }
}
