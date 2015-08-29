package com.franktan.popularmovies.data.movie;

import android.net.Uri;
import android.provider.BaseColumns;

import com.franktan.popularmovies.data.MovieProvider;
import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.trailer.TrailerColumns;

/**
 * movie entries
 */
public class MovieColumns implements BaseColumns {
    public static final String TABLE_NAME = "movie";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * The id of the movie in MovieDB
     */
    public static final String MOVIE_MOVIEDB_ID = "movie_moviedb_id";

    public static final String TITLE = "title";

    public static final String BACKDROP_PATH = "backdrop_path";

    public static final String ORIGINAL_LAN = "original_lan";

    public static final String ORIGINAL_TITLE = "original_title";

    public static final String OVERVIEW = "overview";

    public static final String RELEASE_DATE = "release_date";

    public static final String POSTER_PATH = "poster_path";

    public static final String POPULARITY = "popularity";

    public static final String VOTE_AVERAGE = "vote_average";

    public static final String VOTE_COUNT = "vote_count";


    public static final String DEFAULT_ORDER = TABLE_NAME + "build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_MOVIEDB_ID,
            TITLE,
            BACKDROP_PATH,
            ORIGINAL_LAN,
            ORIGINAL_TITLE,
            OVERVIEW,
            RELEASE_DATE,
            POSTER_PATH,
            POPULARITY,
            VOTE_AVERAGE,
            VOTE_COUNT
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_MOVIEDB_ID) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + MOVIE_MOVIEDB_ID)) return true;
            if (c.equals(TITLE) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + TITLE)) return true;
            if (c.equals(BACKDROP_PATH) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + BACKDROP_PATH)) return true;
            if (c.equals(ORIGINAL_LAN) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + ORIGINAL_LAN)) return true;
            if (c.equals(ORIGINAL_TITLE) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + ORIGINAL_TITLE)) return true;
            if (c.equals(OVERVIEW) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + OVERVIEW)) return true;
            if (c.equals(RELEASE_DATE) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + RELEASE_DATE)) return true;
            if (c.equals(POSTER_PATH) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + POSTER_PATH)) return true;
            if (c.equals(POPULARITY) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + POPULARITY)) return true;
            if (c.equals(VOTE_AVERAGE) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + VOTE_AVERAGE)) return true;
            if (c.equals(VOTE_COUNT) || c.contains("build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res" + VOTE_COUNT)) return true;
        }
        return false;
    }

}
