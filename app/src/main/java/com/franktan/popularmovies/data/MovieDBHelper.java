package com.franktan.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.franktan.popularmovies.data.MovieContract.MovieEntry;

/**
 * Created by tan on 13/08/2015.
 */
public class MovieDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "popular_movie.db";

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL for creating movie table
        final String SQL_CREATE_MOVIE_ENTRY = "CREATE TABLE "+ MovieEntry.TABLE_NAME + " (" +
                MovieEntry._ID                      + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MovieEntry.COLUMN_BACKDROP_PATH     + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_MOVIEDB_ID        + " INTEGER UNIQUE NOT NULL, " +
                MovieEntry.COLUMN_ORIGINAL_LAN      + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_ORIGINAL_TITLE    + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_OVERVIEW          + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_RELEASE_DATE      + " REAL NOT NULL, " +
                MovieEntry.COLUMN_POSTER_PATH       + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_POPULARITY        + " REAL NOT NULL, " +
                MovieEntry.COLUMN_TITLE             + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_VIDEO             + " INTEGER NOT NULL, " +
                MovieEntry.COLUMN_VOTE_AVERAGE      + " REAL NOT NULL, " +
                MovieEntry.COLUMN_VOTE_COUNT        + " INTEGER NOT NULL " +
                ");";
        db.execSQL(SQL_CREATE_MOVIE_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
