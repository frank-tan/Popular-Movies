package com.franktan.popularmovies.data.genre;

import android.net.Uri;
import android.provider.BaseColumns;

import com.franktan.popularmovies.data.MovieProvider;
import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.trailer.TrailerColumns;

/**
 * genres
 */
public class GenreColumns implements BaseColumns {
    public static final String TABLE_NAME = "genre";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * the id of the genre on movieDB
     */
    public static final String GENRE_MOVIEDB_ID = "genre_moviedb_id";

    /**
     * name
     */
    public static final String NAME = "name";


    public static final String DEFAULT_ORDER = TABLE_NAME + "build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            GENRE_MOVIEDB_ID,
            NAME
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(GENRE_MOVIEDB_ID) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + GENRE_MOVIEDB_ID)) return true;
            if (c.equals(NAME) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + NAME)) return true;
        }
        return false;
    }

}
