package com.franktan.popularmovies.data.favorite;

import android.net.Uri;
import android.provider.BaseColumns;

import com.franktan.popularmovies.data.MovieProvider;
import com.franktan.popularmovies.data.favorite.FavoriteColumns;
import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.trailer.TrailerColumns;

/**
 * favorite movie
 */
public class FavoriteColumns implements BaseColumns {
    public static final String TABLE_NAME = "favorite";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * the moviedb id of the favorite movie
     */
    public static final String FAVORITE_MOVIEDB_ID = "favorite_moviedb_id";

    /**
     * the date and time when user favorites this movie
     */
    public static final String CREATED = "created";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            FAVORITE_MOVIEDB_ID,
            CREATED
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(FAVORITE_MOVIEDB_ID) || c.contains("." + FAVORITE_MOVIEDB_ID)) return true;
            if (c.equals(CREATED) || c.contains("." + CREATED)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
