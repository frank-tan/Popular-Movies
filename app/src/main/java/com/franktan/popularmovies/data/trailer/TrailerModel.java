package com.franktan.popularmovies.data.trailer;

import com.franktan.popularmovies.data.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * movie trailers
 */
public interface TrailerModel extends BaseModel {

    /**
     * the title of the trailer
     * Can be {@code null}.
     */
    @Nullable
    String getName();

    /**
     * HD, SD, etc.
     * Can be {@code null}.
     */
    @Nullable
    String getSize();

    /**
     * the youtube id
     * Cannot be {@code null}.
     */
    @NonNull
    String getSource();

    /**
     * trailer, video, etc.
     * Can be {@code null}.
     */
    @Nullable
    String getType();

    /**
     * the movie id
     */
    long getMovieId();
}
