package com.franktan.popularmovies.data.favorite;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.franktan.popularmovies.data.base.AbstractCursor;
import com.franktan.popularmovies.data.movie.MovieColumns;

/**
 * Cursor wrapper for the {@code favorite} table.
 */
@SuppressWarnings("UnnecessaryLocalVariable")
public class FavoriteCursor extends AbstractCursor implements FavoriteModel {
    public FavoriteCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(FavoriteColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * the moviedb id of the favorite movie
     */
    public long getFavoriteMoviedbId() {
        Long res = getLongOrNull(FavoriteColumns.FAVORITE_MOVIEDB_ID);
        if (res == null)
            throw new NullPointerException("The value of 'favorite_moviedb_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * The id of the movie in MovieDB
     */
    public long getMovieMovieMoviedbId() {
        Long res = getLongOrNull(MovieColumns.MOVIE_MOVIEDB_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_moviedb_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getMovieTitle() {
        String res = getStringOrNull(MovieColumns.TITLE);
        if (res == null)
            throw new NullPointerException("The value of 'title' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code backdrop_path} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieBackdropPath() {
        String res = getStringOrNull(MovieColumns.BACKDROP_PATH);
        return res;
    }

    /**
     * Get the {@code original_lan} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieOriginalLan() {
        String res = getStringOrNull(MovieColumns.ORIGINAL_LAN);
        return res;
    }

    /**
     * Get the {@code original_title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieOriginalTitle() {
        String res = getStringOrNull(MovieColumns.ORIGINAL_TITLE);
        return res;
    }

    /**
     * Get the {@code overview} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieOverview() {
        String res = getStringOrNull(MovieColumns.OVERVIEW);
        return res;
    }

    /**
     * Get the {@code release_date} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getMovieReleaseDate() {
        Long res = getLongOrNull(MovieColumns.RELEASE_DATE);
        return res;
    }

    /**
     * Get the {@code poster_path} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMoviePosterPath() {
        String res = getStringOrNull(MovieColumns.POSTER_PATH);
        return res;
    }

    /**
     * Get the {@code popularity} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getMoviePopularity() {
        Double res = getDoubleOrNull(MovieColumns.POPULARITY);
        return res;
    }

    /**
     * Get the {@code vote_average} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getMovieVoteAverage() {
        Double res = getDoubleOrNull(MovieColumns.VOTE_AVERAGE);
        return res;
    }

    /**
     * Get the {@code vote_count} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getMovieVoteCount() {
        Integer res = getIntegerOrNull(MovieColumns.VOTE_COUNT);
        return res;
    }

    /**
     * the date and time when user favorites this movie
     * Can be {@code null}.
     */
    @Nullable
    public Long getCreated() {
        Long res = getLongOrNull(FavoriteColumns.CREATED);
        return res;
    }
}
