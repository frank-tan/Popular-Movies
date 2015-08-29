package com.franktan.popularmovies.data.review;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.franktan.popularmovies.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code review} table.
 */
public class ReviewContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ReviewColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ReviewSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable ReviewSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * the id of the reivew on movieDB
     */
    public ReviewContentValues putReviewMoviedbId(int value) {
        mContentValues.put(ReviewColumns.REVIEW_MOVIEDB_ID, value);
        return this;
    }


    /**
     * author
     */
    public ReviewContentValues putAuthor(@Nullable String value) {
        mContentValues.put(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewContentValues putAuthorNull() {
        mContentValues.putNull(ReviewColumns.AUTHOR);
        return this;
    }

    /**
     * content of the review
     */
    public ReviewContentValues putContent(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("content must not be null");
        mContentValues.put(ReviewColumns.CONTENT, value);
        return this;
    }


    /**
     * url of the original review
     */
    public ReviewContentValues putUrl(@Nullable String value) {
        mContentValues.put(ReviewColumns.URL, value);
        return this;
    }

    public ReviewContentValues putUrlNull() {
        mContentValues.putNull(ReviewColumns.URL);
        return this;
    }

    /**
     * the movie id
     */
    public ReviewContentValues putMovieId(long value) {
        mContentValues.put(ReviewColumns.MOVIE_ID, value);
        return this;
    }

}
