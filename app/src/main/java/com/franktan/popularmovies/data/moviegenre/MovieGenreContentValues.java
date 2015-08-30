package com.franktan.popularmovies.data.moviegenre;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.franktan.popularmovies.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movie_genre} table.
 */
public class MovieGenreContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MovieGenreColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MovieGenreSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MovieGenreSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * the movie id
     */
    public MovieGenreContentValues putMovieId(long value) {
        mContentValues.put(MovieGenreColumns.MOVIE_ID, value);
        return this;
    }


    /**
     * the genre id
     */
    public MovieGenreContentValues putGenreId(long value) {
        mContentValues.put(MovieGenreColumns.GENRE_ID, value);
        return this;
    }

}
