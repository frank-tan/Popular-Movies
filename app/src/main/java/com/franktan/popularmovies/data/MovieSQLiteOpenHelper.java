package com.franktan.popularmovies.data;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.franktan.popularmovies.BuildConfig;
import com.franktan.popularmovies.data.favorite.FavoriteColumns;
import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.trailer.TrailerColumns;

public class MovieSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = MovieSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "popular_movies.db";
    private static final int DATABASE_VERSION = 9;
    private static MovieSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final MovieSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_FAVORITE = "CREATE TABLE IF NOT EXISTS "
            + FavoriteColumns.TABLE_NAME + " ( "
            + FavoriteColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FavoriteColumns.FAVORITE_MOVIEDB_ID + " INTEGER NOT NULL, "
            + FavoriteColumns.CREATED + " INTEGER "
            + ", CONSTRAINT fk_favorite_moviedb_id FOREIGN KEY (" + FavoriteColumns.FAVORITE_MOVIEDB_ID + ") REFERENCES movie (movie_moviedb_id) ON DELETE NO ACTION"
            + ", CONSTRAINT unique_moviedb_id UNIQUE (favorite_moviedb_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_GENRE = "CREATE TABLE IF NOT EXISTS "
            + GenreColumns.TABLE_NAME + " ( "
            + GenreColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GenreColumns.GENRE_MOVIEDB_ID + " INTEGER NOT NULL, "
            + GenreColumns.NAME + " TEXT NOT NULL "
            + " );";

    public static final String SQL_CREATE_TABLE_MOVIE = "CREATE TABLE IF NOT EXISTS "
            + MovieColumns.TABLE_NAME + " ( "
            + MovieColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MovieColumns.MOVIE_MOVIEDB_ID + " INTEGER NOT NULL, "
            + MovieColumns.TITLE + " TEXT NOT NULL, "
            + MovieColumns.BACKDROP_PATH + " TEXT, "
            + MovieColumns.ORIGINAL_LAN + " TEXT, "
            + MovieColumns.ORIGINAL_TITLE + " TEXT, "
            + MovieColumns.OVERVIEW + " TEXT, "
            + MovieColumns.RELEASE_DATE + " INTEGER, "
            + MovieColumns.POSTER_PATH + " TEXT, "
            + MovieColumns.POPULARITY + " REAL, "
            + MovieColumns.VOTE_AVERAGE + " REAL, "
            + MovieColumns.VOTE_COUNT + " INTEGER "
            + ", CONSTRAINT unique_movie_moviedb_id UNIQUE (" + MovieColumns.MOVIE_MOVIEDB_ID + ") ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_INDEX_MOVIE_MOVIE_MOVIEDB_ID = "CREATE INDEX IDX_MOVIE_MOVIE_MOVIEDB_ID "
            + " ON " + MovieColumns.TABLE_NAME + " ( " + MovieColumns.MOVIE_MOVIEDB_ID + " );";

    public static final String SQL_CREATE_INDEX_MOVIE_TITLE = "CREATE INDEX IDX_MOVIE_TITLE "
            + " ON " + MovieColumns.TABLE_NAME + " ( " + MovieColumns.TITLE + " );";

    public static final String SQL_CREATE_TABLE_MOVIE_GENRE = "CREATE TABLE IF NOT EXISTS "
            + MovieGenreColumns.TABLE_NAME + " ( "
            + MovieGenreColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MovieGenreColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + MovieGenreColumns.GENRE_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + MovieGenreColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE CASCADE"
            + ", CONSTRAINT fk_genre_id FOREIGN KEY (" + MovieGenreColumns.GENRE_ID + ") REFERENCES genre (_id) ON DELETE CASCADE"
            + " );";

    public static final String SQL_CREATE_TABLE_REVIEW = "CREATE TABLE IF NOT EXISTS "
            + ReviewColumns.TABLE_NAME + " ( "
            + ReviewColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ReviewColumns.REVIEW_MOVIEDB_ID + " INTEGER NOT NULL, "
            + ReviewColumns.AUTHOR + " TEXT, "
            + ReviewColumns.CONTENT + " TEXT NOT NULL, "
            + ReviewColumns.URL + " TEXT NOT NULL, "
            + ReviewColumns.MOVIE_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT unique_reivew_url UNIQUE (" + ReviewColumns.URL + ") ON CONFLICT IGNORE"
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + ReviewColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE CASCADE"
            + " );";

    public static final String SQL_CREATE_TABLE_TRAILER = "CREATE TABLE IF NOT EXISTS "
            + TrailerColumns.TABLE_NAME + " ( "
            + TrailerColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TrailerColumns.NAME + " TEXT, "
            + TrailerColumns.SIZE + " TEXT, "
            + TrailerColumns.SOURCE + " TEXT NOT NULL, "
            + TrailerColumns.TYPE + " TEXT, "
            + TrailerColumns.MOVIE_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT unique_trailer_source UNIQUE (" + TrailerColumns.SOURCE + ") ON CONFLICT IGNORE"
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + TrailerColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE CASCADE"
            + " );";

    // @formatter:on

    public static MovieSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static MovieSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static MovieSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new MovieSQLiteOpenHelper(context);
    }

    private MovieSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new MovieSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static MovieSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new MovieSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private MovieSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new MovieSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_FAVORITE);
        db.execSQL(SQL_CREATE_TABLE_GENRE);
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_INDEX_MOVIE_MOVIE_MOVIEDB_ID);
        db.execSQL(SQL_CREATE_INDEX_MOVIE_TITLE);
        db.execSQL(SQL_CREATE_TABLE_MOVIE_GENRE);
        db.execSQL(SQL_CREATE_TABLE_REVIEW);
        db.execSQL(SQL_CREATE_TABLE_TRAILER);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
        onCreate(db);
    }
}
