package com.franktan.popularmovies.data.trailer;

import android.net.Uri;
import android.provider.BaseColumns;

import com.franktan.popularmovies.data.MovieProvider;
import com.franktan.popularmovies.data.movie.MovieColumns;

/**
 * movie trailers
 */
public class TrailerColumns implements BaseColumns {
    public static final String TABLE_NAME = "trailer";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * the title of the trailer
     */
    public static final String NAME = "name";

    /**
     * HD, SD, etc.
     */
    public static final String SIZE = "size";

    /**
     * the youtube id
     */
    public static final String SOURCE = "source";

    /**
     * trailer, video, etc.
     */
    public static final String TYPE = "type";

    /**
     * the movie id
     */
    public static final String MOVIE_ID = "movie_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            NAME,
            SIZE,
            SOURCE,
            TYPE,
            MOVIE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(SIZE) || c.contains("." + SIZE)) return true;
            if (c.equals(SOURCE) || c.contains("." + SOURCE)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
