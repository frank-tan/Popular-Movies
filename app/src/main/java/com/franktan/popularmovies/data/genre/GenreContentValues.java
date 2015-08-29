package com.franktan.popularmovies.data.genre;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.franktan.popularmovies.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code genre} table.
 */
public class GenreContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return GenreColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable GenreSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable GenreSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * the id of the genre on movieDB
     */
    public GenreContentValues putGenreMoviedbId(int value) {
        mContentValues.put(GenreColumns.GENRE_MOVIEDB_ID, value);
        return this;
    }


    /**
     * name
     */
    public GenreContentValues putName(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("name must not be null");
        mContentValues.put(GenreColumns.NAME, value);
        return this;
    }

}
