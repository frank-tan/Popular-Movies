package com.franktan.popularmovies.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.franktan.popularmovies.data.MovieContract;
import com.franktan.popularmovies.model.Movie;
import com.franktan.popularmovies.util.Parser;

import org.json.JSONException;

import java.text.ParseException;
import java.util.List;

/**
 * Created by tan on 16/08/2015.
 */
public class MovieSyncAdapter extends AbstractThreadedSyncAdapter {
    public static int syncMovieList(Context context, MovieDbAPISyncService movieDbAPISyncService, String sortBy, Long releaseDateFrom) {
        String movieJsonString = movieDbAPISyncService.getMovieInfoFromAPI(context,sortBy,releaseDateFrom);
        List<Movie> movieList;
        try {
            movieList = Parser.parseJson(movieJsonString);
        } catch (JSONException e) {
            // TODO: need to notify developer
            return 0;
        } catch (ParseException e) {
            // TODO: need to notify developer
            return 0;
        }
        ContentValues[] movieContentValues = Parser.contentValuesFromMovieList(movieList);
        return context.getContentResolver().bulkInsert(MovieContract.MovieEntry.CONTENT_URI, movieContentValues);
    }

    public MovieSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

    }
}
