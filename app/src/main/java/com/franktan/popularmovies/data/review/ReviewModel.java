package com.franktan.popularmovies.data.review;

import com.franktan.popularmovies.data.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * movie reviews
 */
public interface ReviewModel extends BaseModel {

    /**
     * the id of the reivew on movieDB
     */
    int getReviewMoviedbId();

    /**
     * author
     * Can be {@code null}.
     */
    @Nullable
    String getAuthor();

    /**
     * content of the review
     * Cannot be {@code null}.
     */
    @NonNull
    String getContent();

    /**
     * url of the original review
     * Can be {@code null}.
     */
    @Nullable
    String getUrl();

    /**
     * the movie id
     */
    long getMovieId();
}
