package com.franktan.popularmovies.data;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.franktan.popularmovies.BuildConfig;
import com.franktan.popularmovies.data.base.BaseContentProvider;
import com.franktan.popularmovies.data.favorite.FavoriteColumns;
import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.trailer.TrailerColumns;
import com.franktan.popularmovies.util.Constants;

import java.util.Arrays;

public class MovieProvider extends BaseContentProvider {
    private static final String TAG = MovieProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.franktan.popularmovies";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_FAVORITE = 0;
    private static final int URI_TYPE_FAVORITE_ID = 1;

    private static final int URI_TYPE_GENRE = 2;
    private static final int URI_TYPE_GENRE_ID = 3;

    private static final int URI_TYPE_MOVIE = 4;
    private static final int URI_TYPE_MOVIE_ID = 5;

    private static final int URI_TYPE_MOVIE_GENRE = 6;
    private static final int URI_TYPE_MOVIE_GENRE_ID = 7;

    private static final int URI_TYPE_REVIEW = 8;
    private static final int URI_TYPE_REVIEW_ID = 9;

    private static final int URI_TYPE_TRAILER = 10;
    private static final int URI_TYPE_TRAILER_ID = 11;

    private static final int URI_TYPE_MOVIEDB_ID = 12;
    private static final int URI_TYPE_MOVIE_WITH_FAVORITE = 13;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, FavoriteColumns.TABLE_NAME, URI_TYPE_FAVORITE);
        URI_MATCHER.addURI(AUTHORITY, FavoriteColumns.TABLE_NAME + "/#", URI_TYPE_FAVORITE_ID);
        URI_MATCHER.addURI(AUTHORITY, GenreColumns.TABLE_NAME, URI_TYPE_GENRE);
        URI_MATCHER.addURI(AUTHORITY, GenreColumns.TABLE_NAME + "/#", URI_TYPE_GENRE_ID);
        URI_MATCHER.addURI(AUTHORITY, MovieColumns.TABLE_NAME, URI_TYPE_MOVIE);
        URI_MATCHER.addURI(AUTHORITY, MovieColumns.TABLE_NAME + "/with_favorite", URI_TYPE_MOVIE_WITH_FAVORITE);
        URI_MATCHER.addURI(AUTHORITY, MovieColumns.TABLE_NAME + "/#", URI_TYPE_MOVIE_ID);
        URI_MATCHER.addURI(AUTHORITY, MovieColumns.TABLE_NAME + "/moviedb/#", URI_TYPE_MOVIEDB_ID);
        URI_MATCHER.addURI(AUTHORITY, MovieGenreColumns.TABLE_NAME, URI_TYPE_MOVIE_GENRE);
        URI_MATCHER.addURI(AUTHORITY, MovieGenreColumns.TABLE_NAME + "/#", URI_TYPE_MOVIE_GENRE_ID);
        URI_MATCHER.addURI(AUTHORITY, ReviewColumns.TABLE_NAME, URI_TYPE_REVIEW);
        URI_MATCHER.addURI(AUTHORITY, ReviewColumns.TABLE_NAME + "/#", URI_TYPE_REVIEW_ID);
        URI_MATCHER.addURI(AUTHORITY, TrailerColumns.TABLE_NAME, URI_TYPE_TRAILER);
        URI_MATCHER.addURI(AUTHORITY, TrailerColumns.TABLE_NAME + "/#", URI_TYPE_TRAILER_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return MovieSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        Log.i(Constants.APP_NAME,"match number is "+ match);
        switch (match) {
            case URI_TYPE_FAVORITE:
                return TYPE_CURSOR_DIR + FavoriteColumns.TABLE_NAME;
            case URI_TYPE_FAVORITE_ID:
                return TYPE_CURSOR_ITEM + FavoriteColumns.TABLE_NAME;

            case URI_TYPE_GENRE:
                return TYPE_CURSOR_DIR + GenreColumns.TABLE_NAME;
            case URI_TYPE_GENRE_ID:
                return TYPE_CURSOR_ITEM + GenreColumns.TABLE_NAME;

            case URI_TYPE_MOVIE:
                return TYPE_CURSOR_DIR + MovieColumns.TABLE_NAME;
            case URI_TYPE_MOVIE_ID:
                return TYPE_CURSOR_ITEM + MovieColumns.TABLE_NAME;

            case URI_TYPE_MOVIE_GENRE:
                return TYPE_CURSOR_DIR + MovieGenreColumns.TABLE_NAME;
            case URI_TYPE_MOVIE_GENRE_ID:
                return TYPE_CURSOR_ITEM + MovieGenreColumns.TABLE_NAME;

            case URI_TYPE_REVIEW:
                return TYPE_CURSOR_DIR + ReviewColumns.TABLE_NAME;
            case URI_TYPE_REVIEW_ID:
                return TYPE_CURSOR_ITEM + ReviewColumns.TABLE_NAME;

            case URI_TYPE_TRAILER:
                return TYPE_CURSOR_DIR + TrailerColumns.TABLE_NAME;
            case URI_TYPE_TRAILER_ID:
                return TYPE_CURSOR_ITEM + TrailerColumns.TABLE_NAME;

            case URI_TYPE_MOVIEDB_ID:
                return TYPE_CURSOR_ITEM + MovieColumns.TABLE_NAME;
            case URI_TYPE_MOVIE_WITH_FAVORITE:
                return TYPE_CURSOR_DIR + MovieColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_FAVORITE:
            case URI_TYPE_FAVORITE_ID:
                res.table = FavoriteColumns.TABLE_NAME;
                res.idColumn = FavoriteColumns._ID;
                res.tablesWithJoins = FavoriteColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + MovieColumns.TABLE_NAME + " AS " + FavoriteColumns.PREFIX_MOVIE + " ON " + FavoriteColumns.TABLE_NAME + "." + FavoriteColumns.FAVORITE_MOVIEDB_ID + "=" + FavoriteColumns.PREFIX_MOVIE + "." + MovieColumns.MOVIE_MOVIEDB_ID;
                }
                res.orderBy = FavoriteColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_GENRE:
            case URI_TYPE_GENRE_ID:
                res.table = GenreColumns.TABLE_NAME;
                res.idColumn = GenreColumns._ID;
                res.tablesWithJoins = GenreColumns.TABLE_NAME;
                res.orderBy = GenreColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MOVIE:
            case URI_TYPE_MOVIE_ID:
                res.table = MovieColumns.TABLE_NAME;
                res.idColumn = MovieColumns._ID;
                res.tablesWithJoins = MovieColumns.TABLE_NAME;
                res.orderBy = MovieColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MOVIEDB_ID:
                res.table = MovieColumns.TABLE_NAME;
                res.idColumn = MovieColumns._ID;
                res.tablesWithJoins = MovieColumns.TABLE_NAME;
                if (ReviewColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + ReviewColumns.TABLE_NAME + " ON " + MovieColumns.TABLE_NAME + "." + MovieColumns._ID + "=" + ReviewColumns.TABLE_NAME + "." + ReviewColumns.MOVIE_ID;
                }
                if (TrailerColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + TrailerColumns.TABLE_NAME + " ON " + MovieColumns.TABLE_NAME + "." + MovieColumns._ID + "=" + TrailerColumns.TABLE_NAME + "." + TrailerColumns.MOVIE_ID;
                }
                if (FavoriteColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + FavoriteColumns.TABLE_NAME + " ON " + MovieColumns.TABLE_NAME + "." + MovieColumns.MOVIE_MOVIEDB_ID + "=" + FavoriteColumns.TABLE_NAME + "." + FavoriteColumns.FAVORITE_MOVIEDB_ID;
                }
                res.orderBy = MovieColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MOVIE_WITH_FAVORITE:
                res.table = MovieColumns.TABLE_NAME;
                res.idColumn = MovieColumns._ID;
                res.tablesWithJoins = MovieColumns.TABLE_NAME;
                if (FavoriteColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + FavoriteColumns.TABLE_NAME + " ON " + MovieColumns.TABLE_NAME + "." + MovieColumns.MOVIE_MOVIEDB_ID + "=" + FavoriteColumns.TABLE_NAME + "." + FavoriteColumns.FAVORITE_MOVIEDB_ID;
                }
                if (GenreColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN (" +
                            " SELECT " + MovieGenreColumns.TABLE_NAME + "." + MovieGenreColumns.MOVIE_ID + " AS movie_id, " + GenreColumns.TABLE_NAME + "." + GenreColumns.NAME + " AS name FROM " + MovieGenreColumns.TABLE_NAME + " INNER JOIN " + GenreColumns.TABLE_NAME + " ON " + MovieGenreColumns.TABLE_NAME + "." + MovieGenreColumns.GENRE_ID + "=" + GenreColumns.TABLE_NAME + "." + GenreColumns.GENRE_MOVIEDB_ID +
                            ") " + GenreColumns.TABLE_NAME + " ON " + MovieColumns.TABLE_NAME + "." + MovieColumns.MOVIE_MOVIEDB_ID + "=" + GenreColumns.TABLE_NAME + "." + MovieGenreColumns.MOVIE_ID;
                }
                res.orderBy = MovieColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MOVIE_GENRE:
            case URI_TYPE_MOVIE_GENRE_ID:
                res.table = MovieGenreColumns.TABLE_NAME;
                res.idColumn = MovieGenreColumns._ID;
                res.tablesWithJoins = MovieGenreColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + MovieColumns.TABLE_NAME + " AS " + MovieGenreColumns.PREFIX_MOVIE + " ON " + MovieGenreColumns.TABLE_NAME + "." + MovieGenreColumns.MOVIE_ID + "=" + MovieGenreColumns.PREFIX_MOVIE + "." + MovieColumns.MOVIE_MOVIEDB_ID;
                }
                if (GenreColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + GenreColumns.TABLE_NAME + " AS " + MovieGenreColumns.PREFIX_GENRE + " ON " + MovieGenreColumns.TABLE_NAME + "." + MovieGenreColumns.GENRE_ID + "=" + MovieGenreColumns.PREFIX_GENRE + "." + GenreColumns.GENRE_MOVIEDB_ID;
                }
                res.orderBy = MovieGenreColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_REVIEW:
            case URI_TYPE_REVIEW_ID:
                res.table = ReviewColumns.TABLE_NAME;
                res.idColumn = ReviewColumns._ID;
                res.tablesWithJoins = ReviewColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + MovieColumns.TABLE_NAME + " AS " + ReviewColumns.PREFIX_MOVIE + " ON " + ReviewColumns.TABLE_NAME + "." + ReviewColumns.MOVIE_ID + "=" + ReviewColumns.PREFIX_MOVIE + "." + MovieColumns._ID;
                }
                res.orderBy = ReviewColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_TRAILER:
            case URI_TYPE_TRAILER_ID:
                res.table = TrailerColumns.TABLE_NAME;
                res.idColumn = TrailerColumns._ID;
                res.tablesWithJoins = TrailerColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + MovieColumns.TABLE_NAME + " AS " + TrailerColumns.PREFIX_MOVIE + " ON " + TrailerColumns.TABLE_NAME + "." + TrailerColumns.MOVIE_ID + "=" + TrailerColumns.PREFIX_MOVIE + "." + MovieColumns._ID;
                }
                res.orderBy = TrailerColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_FAVORITE_ID:
            case URI_TYPE_GENRE_ID:
            case URI_TYPE_MOVIE_ID:
            case URI_TYPE_MOVIE_GENRE_ID:
            case URI_TYPE_REVIEW_ID:
            case URI_TYPE_TRAILER_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
