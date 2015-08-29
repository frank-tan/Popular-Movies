package com.franktan.popularmovies.data.trailer;

import android.net.Uri;
import android.provider.BaseColumns;

import com.franktan.popularmovies.data.MovieProvider;
import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.trailer.TrailerColumns;

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


    public static final String DEFAULT_ORDER = TABLE_NAME + "build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" +_ID;

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
            if (c.equals(NAME) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + NAME)) return true;
            if (c.equals(SIZE) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + SIZE)) return true;
            if (c.equals(SOURCE) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + SOURCE)) return true;
            if (c.equals(TYPE) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + TYPE)) return true;
            if (c.equals(MOVIE_ID) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + MOVIE_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
