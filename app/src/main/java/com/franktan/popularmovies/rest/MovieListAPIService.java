package com.franktan.popularmovies.rest;

import android.content.Context;
import android.net.Uri;

import com.franktan.popularmovies.model.SortCriterion;
import com.franktan.popularmovies.util.Parser;
import com.franktan.popularmovies.util.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tan on 16/08/2015.
 */
public class MovieListAPIService {

    public String getMovieInfoFromAPI(Context context, SortCriterion sortBy, long releaseDateFrom, long releaseDateTo, int page) {
        HttpURLConnection urlConnection;
        BufferedReader reader;

        String formattedDateFrom = Parser.movieDbDateStringFromMiliseconds(releaseDateFrom);
        String formattedDateTo = Parser.movieDbDateStringFromMiliseconds(releaseDateTo);

        final String MOVIEDB_BASE_URL =
                "http://api.themoviedb.org/3/discover/movie?";
        final String API_KEY = "api_key";
        final String SORT_BY = "sort_by";
        final String PAGE = "page";
        final String VOTE_COUNT_GREATER_THAN = "vote_count.gte";
        final String RELEASE_FROM = "primary_release_date.gte";
        final String RELEASE_TO = "primary_release_date.lte";

        try {
            Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                    .appendQueryParameter(API_KEY, Utilities.getMovieDBApiKey(context))
                    .appendQueryParameter(SORT_BY, sortBy.getValue())
                    .appendQueryParameter(PAGE, String.valueOf(page))
                    .appendQueryParameter(RELEASE_FROM, formattedDateFrom)
                    .appendQueryParameter(RELEASE_TO, formattedDateTo)
                    .appendQueryParameter(VOTE_COUNT_GREATER_THAN, "10")
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
                buffer.append(line).append("\n");
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

    public static MovieListAPIService getDbSyncService() {
        return new MovieListAPIService();
    }
}
