package com.franktan.popularmovies.data.moviegenre;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.franktan.popularmovies.data.base.AbstractSelection;
import com.franktan.popularmovies.data.movie.*;
import com.franktan.popularmovies.data.genre.*;

/**
 * Selection for the {@code movie_genre} table.
 */
public class MovieGenreSelection extends AbstractSelection<MovieGenreSelection> {
    @Override
    protected Uri baseUri() {
        return MovieGenreColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieGenreCursor} object, which is positioned before the first entry, or null.
     */
    public MovieGenreCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieGenreCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MovieGenreCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieGenreCursor} object, which is positioned before the first entry, or null.
     */
    public MovieGenreCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieGenreCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MovieGenreCursor query(Context context) {
        return query(context, null);
    }


    public MovieGenreSelection id(long... value) {
        addEquals("movie_genre." + MovieGenreColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieGenreSelection idNot(long... value) {
        addNotEquals("movie_genre." + MovieGenreColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieGenreSelection orderById(boolean desc) {
        orderBy("movie_genre." + MovieGenreColumns._ID, desc);
        return this;
    }

    public MovieGenreSelection orderById() {
        return orderById(false);
    }

    public MovieGenreSelection movieId(long... value) {
        addEquals(MovieGenreColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieGenreSelection movieIdNot(long... value) {
        addNotEquals(MovieGenreColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieGenreSelection movieIdGt(long value) {
        addGreaterThan(MovieGenreColumns.MOVIE_ID, value);
        return this;
    }

    public MovieGenreSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(MovieGenreColumns.MOVIE_ID, value);
        return this;
    }

    public MovieGenreSelection movieIdLt(long value) {
        addLessThan(MovieGenreColumns.MOVIE_ID, value);
        return this;
    }

    public MovieGenreSelection movieIdLtEq(long value) {
        addLessThanOrEquals(MovieGenreColumns.MOVIE_ID, value);
        return this;
    }

    public MovieGenreSelection orderByMovieId(boolean desc) {
        orderBy(MovieGenreColumns.MOVIE_ID, desc);
        return this;
    }

    public MovieGenreSelection orderByMovieId() {
        orderBy(MovieGenreColumns.MOVIE_ID, false);
        return this;
    }

    public MovieGenreSelection movieMovieMoviedbId(long... value) {
        addEquals(MovieColumns.MOVIE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public MovieGenreSelection movieMovieMoviedbIdNot(long... value) {
        addNotEquals(MovieColumns.MOVIE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public MovieGenreSelection movieMovieMoviedbIdGt(long value) {
        addGreaterThan(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public MovieGenreSelection movieMovieMoviedbIdGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public MovieGenreSelection movieMovieMoviedbIdLt(long value) {
        addLessThan(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public MovieGenreSelection movieMovieMoviedbIdLtEq(long value) {
        addLessThanOrEquals(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public MovieGenreSelection orderByMovieMovieMoviedbId(boolean desc) {
        orderBy(MovieColumns.MOVIE_MOVIEDB_ID, desc);
        return this;
    }

    public MovieGenreSelection orderByMovieMovieMoviedbId() {
        orderBy(MovieColumns.MOVIE_MOVIEDB_ID, false);
        return this;
    }

    public MovieGenreSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public MovieGenreSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public MovieGenreSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public MovieGenreSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public MovieGenreSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public MovieGenreSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public MovieGenreSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public MovieGenreSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public MovieGenreSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieGenreSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieGenreSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieGenreSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieGenreSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieGenreSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieGenreSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public MovieGenreSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public MovieGenreSelection movieOriginalLan(String... value) {
        addEquals(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public MovieGenreSelection movieOriginalLanNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public MovieGenreSelection movieOriginalLanLike(String... value) {
        addLike(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public MovieGenreSelection movieOriginalLanContains(String... value) {
        addContains(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public MovieGenreSelection movieOriginalLanStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public MovieGenreSelection movieOriginalLanEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public MovieGenreSelection orderByMovieOriginalLan(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_LAN, desc);
        return this;
    }

    public MovieGenreSelection orderByMovieOriginalLan() {
        orderBy(MovieColumns.ORIGINAL_LAN, false);
        return this;
    }

    public MovieGenreSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieGenreSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieGenreSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieGenreSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieGenreSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieGenreSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieGenreSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public MovieGenreSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public MovieGenreSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieGenreSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieGenreSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieGenreSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieGenreSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieGenreSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieGenreSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public MovieGenreSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public MovieGenreSelection movieReleaseDate(Long... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieGenreSelection movieReleaseDateNot(Long... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieGenreSelection movieReleaseDateGt(long value) {
        addGreaterThan(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieGenreSelection movieReleaseDateGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieGenreSelection movieReleaseDateLt(long value) {
        addLessThan(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieGenreSelection movieReleaseDateLtEq(long value) {
        addLessThanOrEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieGenreSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public MovieGenreSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public MovieGenreSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieGenreSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieGenreSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieGenreSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieGenreSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieGenreSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieGenreSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public MovieGenreSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public MovieGenreSelection moviePopularity(Double... value) {
        addEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieGenreSelection moviePopularityNot(Double... value) {
        addNotEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieGenreSelection moviePopularityGt(double value) {
        addGreaterThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieGenreSelection moviePopularityGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieGenreSelection moviePopularityLt(double value) {
        addLessThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieGenreSelection moviePopularityLtEq(double value) {
        addLessThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieGenreSelection orderByMoviePopularity(boolean desc) {
        orderBy(MovieColumns.POPULARITY, desc);
        return this;
    }

    public MovieGenreSelection orderByMoviePopularity() {
        orderBy(MovieColumns.POPULARITY, false);
        return this;
    }

    public MovieGenreSelection movieVoteAverage(Double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieGenreSelection movieVoteAverageNot(Double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieGenreSelection movieVoteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieGenreSelection movieVoteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieGenreSelection movieVoteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieGenreSelection movieVoteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieGenreSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public MovieGenreSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public MovieGenreSelection movieVoteCount(Integer... value) {
        addEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieGenreSelection movieVoteCountNot(Integer... value) {
        addNotEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieGenreSelection movieVoteCountGt(int value) {
        addGreaterThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieGenreSelection movieVoteCountGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieGenreSelection movieVoteCountLt(int value) {
        addLessThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieGenreSelection movieVoteCountLtEq(int value) {
        addLessThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieGenreSelection orderByMovieVoteCount(boolean desc) {
        orderBy(MovieColumns.VOTE_COUNT, desc);
        return this;
    }

    public MovieGenreSelection orderByMovieVoteCount() {
        orderBy(MovieColumns.VOTE_COUNT, false);
        return this;
    }

    public MovieGenreSelection genreId(long... value) {
        addEquals(MovieGenreColumns.GENRE_ID, toObjectArray(value));
        return this;
    }

    public MovieGenreSelection genreIdNot(long... value) {
        addNotEquals(MovieGenreColumns.GENRE_ID, toObjectArray(value));
        return this;
    }

    public MovieGenreSelection genreIdGt(long value) {
        addGreaterThan(MovieGenreColumns.GENRE_ID, value);
        return this;
    }

    public MovieGenreSelection genreIdGtEq(long value) {
        addGreaterThanOrEquals(MovieGenreColumns.GENRE_ID, value);
        return this;
    }

    public MovieGenreSelection genreIdLt(long value) {
        addLessThan(MovieGenreColumns.GENRE_ID, value);
        return this;
    }

    public MovieGenreSelection genreIdLtEq(long value) {
        addLessThanOrEquals(MovieGenreColumns.GENRE_ID, value);
        return this;
    }

    public MovieGenreSelection orderByGenreId(boolean desc) {
        orderBy(MovieGenreColumns.GENRE_ID, desc);
        return this;
    }

    public MovieGenreSelection orderByGenreId() {
        orderBy(MovieGenreColumns.GENRE_ID, false);
        return this;
    }

    public MovieGenreSelection genreGenreMoviedbId(int... value) {
        addEquals(GenreColumns.GENRE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public MovieGenreSelection genreGenreMoviedbIdNot(int... value) {
        addNotEquals(GenreColumns.GENRE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public MovieGenreSelection genreGenreMoviedbIdGt(int value) {
        addGreaterThan(GenreColumns.GENRE_MOVIEDB_ID, value);
        return this;
    }

    public MovieGenreSelection genreGenreMoviedbIdGtEq(int value) {
        addGreaterThanOrEquals(GenreColumns.GENRE_MOVIEDB_ID, value);
        return this;
    }

    public MovieGenreSelection genreGenreMoviedbIdLt(int value) {
        addLessThan(GenreColumns.GENRE_MOVIEDB_ID, value);
        return this;
    }

    public MovieGenreSelection genreGenreMoviedbIdLtEq(int value) {
        addLessThanOrEquals(GenreColumns.GENRE_MOVIEDB_ID, value);
        return this;
    }

    public MovieGenreSelection orderByGenreGenreMoviedbId(boolean desc) {
        orderBy(GenreColumns.GENRE_MOVIEDB_ID, desc);
        return this;
    }

    public MovieGenreSelection orderByGenreGenreMoviedbId() {
        orderBy(GenreColumns.GENRE_MOVIEDB_ID, false);
        return this;
    }

    public MovieGenreSelection genreName(String... value) {
        addEquals(GenreColumns.NAME, value);
        return this;
    }

    public MovieGenreSelection genreNameNot(String... value) {
        addNotEquals(GenreColumns.NAME, value);
        return this;
    }

    public MovieGenreSelection genreNameLike(String... value) {
        addLike(GenreColumns.NAME, value);
        return this;
    }

    public MovieGenreSelection genreNameContains(String... value) {
        addContains(GenreColumns.NAME, value);
        return this;
    }

    public MovieGenreSelection genreNameStartsWith(String... value) {
        addStartsWith(GenreColumns.NAME, value);
        return this;
    }

    public MovieGenreSelection genreNameEndsWith(String... value) {
        addEndsWith(GenreColumns.NAME, value);
        return this;
    }

    public MovieGenreSelection orderByGenreName(boolean desc) {
        orderBy(GenreColumns.NAME, desc);
        return this;
    }

    public MovieGenreSelection orderByGenreName() {
        orderBy(GenreColumns.NAME, false);
        return this;
    }
}
