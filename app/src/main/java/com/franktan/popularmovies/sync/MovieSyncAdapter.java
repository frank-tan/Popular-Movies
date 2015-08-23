package com.franktan.popularmovies.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.franktan.popularmovies.R;
import com.franktan.popularmovies.data.MovieContract;
import com.franktan.popularmovies.model.Movie;
import com.franktan.popularmovies.util.Constants;
import com.franktan.popularmovies.util.Parser;

import org.json.JSONException;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tan on 16/08/2015.
 */
public class MovieSyncAdapter extends AbstractThreadedSyncAdapter {

    // Sync every 24 hours
    private static final long SYNC_INTERVAL = (long) 24 * 60 * 60;

    // With 2 hours flexible time
    private static final long SYNC_FLEXTIME = (long) 4 * 60 * 60;

    ContentResolver mContentResolver;

    /**
     * Create or get existing sync account and schedule a periodic sync
     * @param context
     */
    public static void initialize(Context context) {
        Log.i(Constants.APP_NAME,"initializing sync adapter");

        Account account = getSyncAccount(context);

        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        Context.ACCOUNT_SERVICE);

        // if the account type does not exist, add a new account
        if (accountManager.getAccountsByType(context.getString(R.string.sync_account_type)).length == 0) {
            Log.i(Constants.APP_NAME,"account does not exist. Add new account ");
            /*
             * Add the account and account type, no password or user data
             * If successful, return the Account object, otherwise report an error.
             */
            if (!accountManager.addAccountExplicitly(account, null, null)) {
                /*
                 * If you don't set android:syncable="true" in
                 * in your <provider> element in the manifest,
                 * then call context.setIsSyncable(account, AUTHORITY, 1)
                 * here.
                 */
                Log.i(Constants.APP_NAME,"failed to add new account");
            }
            //account added successfully, set periodical sync
            Log.i(Constants.APP_NAME, "add new account successful");
            setPeriodicSync(context, account);
            syncMovieDataNow(context);
        }
    }

    public static int retrieveAndSaveMovieData(Context context, MovieDbRESTAPIService movieDbRESTAPIService, String sortBy, Long releaseDateFrom, int page) {
        String movieJsonString = movieDbRESTAPIService.getMovieInfoFromAPI(context,sortBy,releaseDateFrom, page);
        Log.i(Constants.APP_NAME,movieJsonString);
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
        mContentResolver = context.getContentResolver();
    }

    public MovieSyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        MovieDbRESTAPIService movieDbRESTAPIService = MovieDbRESTAPIService.getDbSyncService();

        //TODO: find a suitable way to retrieve movie data

        String sortBy = "popularity.desc";

        // get movies from within 6 months ago
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.MONTH, -6);
        Date twoYearsAgo = cal.getTime();
        long dateFrom = twoYearsAgo.getTime();

        for(int page = 1; page <= Constants.PAGES_NEEDED; page ++) {
            retrieveAndSaveMovieData(getContext(), movieDbRESTAPIService, sortBy, dateFrom, page);
        }
    }

    public static void syncMovieDataNow (Context context) {
    // Pass the settings flags by inserting them in a bundle
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        /*
         * Request the sync for the default account, authority, and
         * manual sync settings
         */
        Log.i(Constants.APP_NAME, "Request sync");
        ContentResolver.requestSync(getSyncAccount(context), context.getString(R.string.content_authority), settingsBundle);
        Log.i(Constants.APP_NAME, "sync requested");
    }

    /**
     * Create an account for sync adapter
     * @param context
     * @return
     */
    private static Account getSyncAccount(Context context) {
        // Create the account type and default account
        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        return newAccount;
    }

    /**
     * Set synchronisation everyday with 4 hours flexible time
     * @param context
     * @param account
     */
    private static void setPeriodicSync(Context context, Account account) {
        String authority = context.getResources().getString(R.string.content_authority);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(SYNC_INTERVAL, SYNC_FLEXTIME).
                    setSyncAdapter(account, authority).
                    setExtras(Bundle.EMPTY).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(
                    account,
                    authority,
                    Bundle.EMPTY,
                    SYNC_INTERVAL);
        }

        ContentResolver.setSyncAutomatically(account, context.getString(R.string.content_authority), true);
    }
}
