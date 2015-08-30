package com.franktan.popularmovies.data.moviegenre;

import com.franktan.popularmovies.data.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * movie genre intersection table
 */
public interface MovieGenreModel extends BaseModel {

    /**
     * the movie id
     */
    long getMovieId();

    /**
     * the genre id
     */
    long getGenreId();
}
