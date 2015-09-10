package com.franktan.popularmovies.data.favorite;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.franktan.popularmovies.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code favorite} table.
 */
public class FavoriteContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return FavoriteColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable FavoriteSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable FavoriteSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * the moviedb id of the favorite movie
     */
    public FavoriteContentValues putFavoriteMoviedbId(long value) {
        mContentValues.put(FavoriteColumns.FAVORITE_MOVIEDB_ID, value);
        return this;
    }


    /**
     * the date and time when user favorites this movie
     */
    public FavoriteContentValues putCreated(@Nullable Long value) {
        mContentValues.put(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteContentValues putCreatedNull() {
        mContentValues.putNull(FavoriteColumns.CREATED);
        return this;
    }
}
