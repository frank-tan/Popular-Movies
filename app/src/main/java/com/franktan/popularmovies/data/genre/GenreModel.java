package com.franktan.popularmovies.data.genre;

import com.franktan.popularmovies.data.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * genres
 */
public interface GenreModel extends BaseModel {

    /**
     * the id of the genre on movieDB
     */
    int getGenreMoviedbId();

    /**
     * name
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();
}
