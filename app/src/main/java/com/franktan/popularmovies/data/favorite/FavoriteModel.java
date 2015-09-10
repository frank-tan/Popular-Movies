package com.franktan.popularmovies.data.favorite;

import android.support.annotation.Nullable;

import com.franktan.popularmovies.data.base.BaseModel;

/**
 * favorite movie
 */
public interface FavoriteModel extends BaseModel {

    /**
     * the moviedb id of the favorite movie
     */
    long getFavoriteMoviedbId();

    /**
     * the date and time when user favorites this movie
     * Can be {@code null}.
     */
    @Nullable
    Long getCreated();
}
