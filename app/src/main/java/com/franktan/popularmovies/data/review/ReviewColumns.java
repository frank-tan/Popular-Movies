package com.franktan.popularmovies.data.review;

import android.net.Uri;
import android.provider.BaseColumns;

import com.franktan.popularmovies.data.MovieProvider;
import com.franktan.popularmovies.data.movie.MovieColumns;

/**
 * movie reviews
 */
public class ReviewColumns implements BaseColumns {
    public static final String TABLE_NAME = "review";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * the id of the reivew on movieDB
     */
    public static final String REVIEW_MOVIEDB_ID = "review_moviedb_id";

    /**
     * author
     */
    public static final String AUTHOR = "author";

    /**
     * content of the review
     */
    public static final String CONTENT = "content";

    /**
     * url of the original review
     */
    public static final String URL = "url";

    /**
     * the movie id
     */
    public static final String MOVIE_ID = "movie_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            REVIEW_MOVIEDB_ID,
            AUTHOR,
            CONTENT,
            URL,
            MOVIE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(REVIEW_MOVIEDB_ID) || c.contains("." + REVIEW_MOVIEDB_ID)) return true;
            if (c.equals(AUTHOR) || c.contains("." + AUTHOR)) return true;
            if (c.equals(CONTENT) || c.contains("." + CONTENT)) return true;
            if (c.equals(URL) || c.contains("." + URL)) return true;
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
