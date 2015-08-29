package com.franktan.popularmovies.data.genre;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.franktan.popularmovies.data.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code genre} table.
 */
public class GenreCursor extends AbstractCursor implements GenreModel {
    public GenreCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(GenreColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * the id of the genre on movieDB
     */
    public int getGenreMoviedbId() {
        Integer res = getIntegerOrNull(GenreColumns.GENRE_MOVIEDB_ID);
        if (res == null)
            throw new NullPointerException("The value of 'genre_moviedb_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * name
     * Cannot be {@code null}.
     */
    @NonNull
    public String getName() {
        String res = getStringOrNull(GenreColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
