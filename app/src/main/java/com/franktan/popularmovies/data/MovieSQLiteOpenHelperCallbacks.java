package com.franktan.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import com.franktan.popularmovies.BuildConfig;
import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.trailer.TrailerColumns;

/**
 * Implement your custom database creation or upgrade code here.
 *
 * This file will not be overwritten if you re-run the content provider generator.
 */
public class MovieSQLiteOpenHelperCallbacks {
    private static final String TAG = MovieSQLiteOpenHelperCallbacks.class.getSimpleName();

    public void onOpen(final Context context, final SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onOpen");
        // Insert your db open code here.
    }

    public void onPreCreate(final Context context, final SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onPreCreate");
        // Insert your db creation code here. This is called before your tables are created.
    }

    public void onPostCreate(final Context context, final SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onPostCreate");
        // Insert your db creation code here. This is called after your tables are created.
    }

    public void onUpgrade(final Context context, final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        if (BuildConfig.DEBUG) Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        // Insert your upgrading code here.
        if(newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TrailerColumns.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + ReviewColumns.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + MovieGenreColumns.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + GenreColumns.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + MovieColumns.TABLE_NAME);
        }
    }
}
