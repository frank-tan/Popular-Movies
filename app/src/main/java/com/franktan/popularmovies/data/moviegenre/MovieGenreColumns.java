package com.franktan.popularmovies.data.moviegenre;

import android.net.Uri;
import android.provider.BaseColumns;

import com.franktan.popularmovies.data.MovieProvider;
import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.trailer.TrailerColumns;

/**
 * movie genre intersection table
 */
public class MovieGenreColumns implements BaseColumns {
    public static final String TABLE_NAME = "movie_genre";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * the movie id
     */
    public static final String MOVIE_ID = "movie_id";

    /**
     * the genre id
     */
    public static final String GENRE_ID = "genre_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            GENRE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + MOVIE_ID)) return true;
            if (c.equals(GENRE_ID) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + GENRE_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
    public static final String PREFIX_GENRE = TABLE_NAME + "__" + GenreColumns.TABLE_NAME;
}
