package com.franktan.popularmovies.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

import com.franktan.popularmovies.R;

/**
 * Defines database tables and columns names
 * Created by tan on 13/08/2015.
 */
public class MovieContract {
    public static final String CONTENT_AUTHORITY = Resources.getSystem().getString(R.string.content_authority);
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIE = "movie";

    //TODO: get rid of Time
    public static long normalizeDate(long startDate) {
        // normalize the start date to the beginning of the (UTC) day
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }

    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;

        // Table name
        public static final String TABLE_NAME = "movie";

        public static final String COLUMN_BACKDROP_PATH     = "column_backdrop_path";
        public static final String COLUMN_MOVIEDB_ID        = "column_moviedb_id";
        public static final String COLUMN_ORIGINAL_LAN      = "column_original_lan";
        public static final String COLUMN_ORIGINAL_TITLE    = "column_original_title";
        public static final String COLUMN_OVERVIEW          = "column_overview";
        public static final String COLUMN_RELEASE_DATE      = "column_release_date";
        public static final String COLUMN_POSTER_PATH       = "column_poster_path";
        public static final String COLUMN_POPULARITY        = "column_popularity";
        public static final String COLUMN_TITLE             = "column_title";
        public static final String COLUMN_VIDEO             = "column_video";
        public static final String COLUMN_VOTE_AVERAGE      = "column_vote_average";
        public static final String COLUMN_VOTE_COUNT        = "column_vote_count";

        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
