package com.franktan.popularmovies.data.trailer;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.franktan.popularmovies.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code trailer} table.
 */
public class TrailerContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return TrailerColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable TrailerSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable TrailerSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * the title of the trailer
     */
    public TrailerContentValues putName(@Nullable String value) {
        mContentValues.put(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerContentValues putNameNull() {
        mContentValues.putNull(TrailerColumns.NAME);
        return this;
    }

    /**
     * HD, SD, etc.
     */
    public TrailerContentValues putSize(@Nullable String value) {
        mContentValues.put(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerContentValues putSizeNull() {
        mContentValues.putNull(TrailerColumns.SIZE);
        return this;
    }

    /**
     * the youtube id
     */
    public TrailerContentValues putSource(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("source must not be null");
        mContentValues.put(TrailerColumns.SOURCE, value);
        return this;
    }


    /**
     * trailer, video, etc.
     */
    public TrailerContentValues putType(@Nullable String value) {
        mContentValues.put(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerContentValues putTypeNull() {
        mContentValues.putNull(TrailerColumns.TYPE);
        return this;
    }

    /**
     * the movie id
     */
    public TrailerContentValues putMovieId(long value) {
        mContentValues.put(TrailerColumns.MOVIE_ID, value);
        return this;
    }

}
