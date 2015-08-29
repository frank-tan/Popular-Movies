package com.franktan.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.franktan.popularmovies.data.MovieContract.MovieEntry;
import com.franktan.popularmovies.data.MovieContract.TrailerEntry;
import com.franktan.popularmovies.data.MovieContract.GenreEntry;
import com.franktan.popularmovies.data.MovieContract.ReviewEntry;

/**
 * Created by tan on 13/08/2015.
 */
public class MovieDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 4;

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
                MovieEntry.COLUMN_RELEASE_DATE      + " INTEGER NOT NULL, " +
                MovieEntry.COLUMN_POSTER_PATH       + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_POPULARITY        + " REAL NOT NULL, " +
                MovieEntry.COLUMN_TITLE             + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_VOTE_AVERAGE      + " REAL NOT NULL, " +
                MovieEntry.COLUMN_VOTE_COUNT        + " INTEGER NOT NULL " +
                ");";


        final String SQL_CREATE_TRAILER_ENTRY = "CREATE TABLE "+ TrailerEntry.TABLE_NAME + " (" +
                TrailerEntry._ID                + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TrailerEntry.COLUMN_MOVIE_ID    + " INTEGER UNIQUE NOT NULL, " +
                TrailerEntry.COLUMN_NAME        + " TEXT, " +
                TrailerEntry.COLUMN_SIZE        + " TEXT, " +
                TrailerEntry.COLUMN_SOURCE      + " TEXT NOT NULL, " +
                TrailerEntry.COLUMN_TYPE        + " TEXT, " +
                ");";

        final String SQL_CREATE_GENRE_ENTRY = "CREATE TABLE "+ GenreEntry.TABLE_NAME + " (" +
                GenreEntry._ID                  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                GenreEntry.COLUMN_MOVIEDB_ID    + " INTEGER NOT NULL, " +
                GenreEntry.COLUMN_NAME          + " TEXT NOT NULL, " +
                ");";

        final String SQL_CREATE_REVIEW_ENTRY = "CREATE TABLE "+ ReviewEntry.TABLE_NAME + " (" +
                ReviewEntry._ID                 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ReviewEntry.COLUMN_MOVIE_ID     + " INTEGER UNIQUE NOT NULL, " +
                ReviewEntry.COLUMN_MOVIEDB_ID   + " INTEGER UNIQUE NOT NULL, " +
                ReviewEntry.COLUMN_AUTHOR       + " TEXT NOT NULL, " +
                ReviewEntry.COLUMN_CONTENT      + " TEXT NOT NULL, " +
                ReviewEntry.COLUMN_URL          + " TEXT, " +
                ");";

        db.execSQL(SQL_CREATE_MOVIE_ENTRY);
        db.execSQL(SQL_CREATE_TRAILER_ENTRY);
        db.execSQL(SQL_CREATE_GENRE_ENTRY);
        db.execSQL(SQL_CREATE_REVIEW_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TrailerEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GenreEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ReviewEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
