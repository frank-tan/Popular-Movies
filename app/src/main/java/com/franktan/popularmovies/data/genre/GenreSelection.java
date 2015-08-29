package com.franktan.popularmovies.data.genre;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.franktan.popularmovies.data.base.AbstractSelection;

/**
 * Selection for the {@code genre} table.
 */
public class GenreSelection extends AbstractSelection<GenreSelection> {
    @Override
    protected Uri baseUri() {
        return GenreColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code GenreCursor} object, which is positioned before the first entry, or null.
     */
    public GenreCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new GenreCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public GenreCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code GenreCursor} object, which is positioned before the first entry, or null.
     */
    public GenreCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new GenreCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public GenreCursor query(Context context) {
        return query(context, null);
    }


    public GenreSelection id(long... value) {
        addEquals("genre." + GenreColumns._ID, toObjectArray(value));
        return this;
    }

    public GenreSelection idNot(long... value) {
        addNotEquals("genre." + GenreColumns._ID, toObjectArray(value));
        return this;
    }

    public GenreSelection orderById(boolean desc) {
        orderBy("genre." + GenreColumns._ID, desc);
        return this;
    }

    public GenreSelection orderById() {
        return orderById(false);
    }

    public GenreSelection genreMoviedbId(int... value) {
        addEquals(GenreColumns.GENRE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public GenreSelection genreMoviedbIdNot(int... value) {
        addNotEquals(GenreColumns.GENRE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public GenreSelection genreMoviedbIdGt(int value) {
        addGreaterThan(GenreColumns.GENRE_MOVIEDB_ID, value);
        return this;
    }

    public GenreSelection genreMoviedbIdGtEq(int value) {
        addGreaterThanOrEquals(GenreColumns.GENRE_MOVIEDB_ID, value);
        return this;
    }

    public GenreSelection genreMoviedbIdLt(int value) {
        addLessThan(GenreColumns.GENRE_MOVIEDB_ID, value);
        return this;
    }

    public GenreSelection genreMoviedbIdLtEq(int value) {
        addLessThanOrEquals(GenreColumns.GENRE_MOVIEDB_ID, value);
        return this;
    }

    public GenreSelection orderByGenreMoviedbId(boolean desc) {
        orderBy(GenreColumns.GENRE_MOVIEDB_ID, desc);
        return this;
    }

    public GenreSelection orderByGenreMoviedbId() {
        orderBy(GenreColumns.GENRE_MOVIEDB_ID, false);
        return this;
    }

    public GenreSelection name(String... value) {
        addEquals(GenreColumns.NAME, value);
        return this;
    }

    public GenreSelection nameNot(String... value) {
        addNotEquals(GenreColumns.NAME, value);
        return this;
    }

    public GenreSelection nameLike(String... value) {
        addLike(GenreColumns.NAME, value);
        return this;
    }

    public GenreSelection nameContains(String... value) {
        addContains(GenreColumns.NAME, value);
        return this;
    }

    public GenreSelection nameStartsWith(String... value) {
        addStartsWith(GenreColumns.NAME, value);
        return this;
    }

    public GenreSelection nameEndsWith(String... value) {
        addEndsWith(GenreColumns.NAME, value);
        return this;
    }

    public GenreSelection orderByName(boolean desc) {
        orderBy(GenreColumns.NAME, desc);
        return this;
    }

    public GenreSelection orderByName() {
        orderBy(GenreColumns.NAME, false);
        return this;
    }
}
