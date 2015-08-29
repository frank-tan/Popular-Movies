package com.franktan.popularmovies.data.trailer;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.franktan.popularmovies.data.base.AbstractSelection;
import com.franktan.popularmovies.data.movie.*;

/**
 * Selection for the {@code trailer} table.
 */
public class TrailerSelection extends AbstractSelection<TrailerSelection> {
    @Override
    protected Uri baseUri() {
        return TrailerColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TrailerCursor} object, which is positioned before the first entry, or null.
     */
    public TrailerCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TrailerCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public TrailerCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TrailerCursor} object, which is positioned before the first entry, or null.
     */
    public TrailerCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TrailerCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public TrailerCursor query(Context context) {
        return query(context, null);
    }


    public TrailerSelection id(long... value) {
        addEquals("trailer." + TrailerColumns._ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection idNot(long... value) {
        addNotEquals("trailer." + TrailerColumns._ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection orderById(boolean desc) {
        orderBy("trailer." + TrailerColumns._ID, desc);
        return this;
    }

    public TrailerSelection orderById() {
        return orderById(false);
    }

    public TrailerSelection name(String... value) {
        addEquals(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameNot(String... value) {
        addNotEquals(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameLike(String... value) {
        addLike(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameContains(String... value) {
        addContains(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameStartsWith(String... value) {
        addStartsWith(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameEndsWith(String... value) {
        addEndsWith(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection orderByName(boolean desc) {
        orderBy(TrailerColumns.NAME, desc);
        return this;
    }

    public TrailerSelection orderByName() {
        orderBy(TrailerColumns.NAME, false);
        return this;
    }

    public TrailerSelection size(String... value) {
        addEquals(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection sizeNot(String... value) {
        addNotEquals(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection sizeLike(String... value) {
        addLike(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection sizeContains(String... value) {
        addContains(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection sizeStartsWith(String... value) {
        addStartsWith(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection sizeEndsWith(String... value) {
        addEndsWith(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection orderBySize(boolean desc) {
        orderBy(TrailerColumns.SIZE, desc);
        return this;
    }

    public TrailerSelection orderBySize() {
        orderBy(TrailerColumns.SIZE, false);
        return this;
    }

    public TrailerSelection source(String... value) {
        addEquals(TrailerColumns.SOURCE, value);
        return this;
    }

    public TrailerSelection sourceNot(String... value) {
        addNotEquals(TrailerColumns.SOURCE, value);
        return this;
    }

    public TrailerSelection sourceLike(String... value) {
        addLike(TrailerColumns.SOURCE, value);
        return this;
    }

    public TrailerSelection sourceContains(String... value) {
        addContains(TrailerColumns.SOURCE, value);
        return this;
    }

    public TrailerSelection sourceStartsWith(String... value) {
        addStartsWith(TrailerColumns.SOURCE, value);
        return this;
    }

    public TrailerSelection sourceEndsWith(String... value) {
        addEndsWith(TrailerColumns.SOURCE, value);
        return this;
    }

    public TrailerSelection orderBySource(boolean desc) {
        orderBy(TrailerColumns.SOURCE, desc);
        return this;
    }

    public TrailerSelection orderBySource() {
        orderBy(TrailerColumns.SOURCE, false);
        return this;
    }

    public TrailerSelection type(String... value) {
        addEquals(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection typeNot(String... value) {
        addNotEquals(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection typeLike(String... value) {
        addLike(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection typeContains(String... value) {
        addContains(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection typeStartsWith(String... value) {
        addStartsWith(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection typeEndsWith(String... value) {
        addEndsWith(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection orderByType(boolean desc) {
        orderBy(TrailerColumns.TYPE, desc);
        return this;
    }

    public TrailerSelection orderByType() {
        orderBy(TrailerColumns.TYPE, false);
        return this;
    }

    public TrailerSelection movieId(long... value) {
        addEquals(TrailerColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieIdNot(long... value) {
        addNotEquals(TrailerColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieIdGt(long value) {
        addGreaterThan(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdLt(long value) {
        addLessThan(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdLtEq(long value) {
        addLessThanOrEquals(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection orderByMovieId(boolean desc) {
        orderBy(TrailerColumns.MOVIE_ID, desc);
        return this;
    }

    public TrailerSelection orderByMovieId() {
        orderBy(TrailerColumns.MOVIE_ID, false);
        return this;
    }

    public TrailerSelection movieMovieMoviedbId(long... value) {
        addEquals(MovieColumns.MOVIE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieMovieMoviedbIdNot(long... value) {
        addNotEquals(MovieColumns.MOVIE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieMovieMoviedbIdGt(long value) {
        addGreaterThan(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public TrailerSelection movieMovieMoviedbIdGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public TrailerSelection movieMovieMoviedbIdLt(long value) {
        addLessThan(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public TrailerSelection movieMovieMoviedbIdLtEq(long value) {
        addLessThanOrEquals(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public TrailerSelection orderByMovieMovieMoviedbId(boolean desc) {
        orderBy(MovieColumns.MOVIE_MOVIEDB_ID, desc);
        return this;
    }

    public TrailerSelection orderByMovieMovieMoviedbId() {
        orderBy(MovieColumns.MOVIE_MOVIEDB_ID, false);
        return this;
    }

    public TrailerSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public TrailerSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public TrailerSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public TrailerSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public TrailerSelection movieOriginalLan(String... value) {
        addEquals(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public TrailerSelection movieOriginalLanNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public TrailerSelection movieOriginalLanLike(String... value) {
        addLike(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public TrailerSelection movieOriginalLanContains(String... value) {
        addContains(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public TrailerSelection movieOriginalLanStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public TrailerSelection movieOriginalLanEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public TrailerSelection orderByMovieOriginalLan(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_LAN, desc);
        return this;
    }

    public TrailerSelection orderByMovieOriginalLan() {
        orderBy(MovieColumns.ORIGINAL_LAN, false);
        return this;
    }

    public TrailerSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public TrailerSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public TrailerSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public TrailerSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public TrailerSelection movieReleaseDate(Long... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection movieReleaseDateNot(Long... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection movieReleaseDateGt(long value) {
        addGreaterThan(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection movieReleaseDateGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection movieReleaseDateLt(long value) {
        addLessThan(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection movieReleaseDateLtEq(long value) {
        addLessThanOrEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public TrailerSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public TrailerSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public TrailerSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public TrailerSelection moviePopularity(Double... value) {
        addEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public TrailerSelection moviePopularityNot(Double... value) {
        addNotEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public TrailerSelection moviePopularityGt(double value) {
        addGreaterThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public TrailerSelection moviePopularityGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public TrailerSelection moviePopularityLt(double value) {
        addLessThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public TrailerSelection moviePopularityLtEq(double value) {
        addLessThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public TrailerSelection orderByMoviePopularity(boolean desc) {
        orderBy(MovieColumns.POPULARITY, desc);
        return this;
    }

    public TrailerSelection orderByMoviePopularity() {
        orderBy(MovieColumns.POPULARITY, false);
        return this;
    }

    public TrailerSelection movieVoteAverage(Double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public TrailerSelection movieVoteAverageNot(Double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public TrailerSelection movieVoteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public TrailerSelection movieVoteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public TrailerSelection movieVoteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public TrailerSelection movieVoteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public TrailerSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public TrailerSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public TrailerSelection movieVoteCount(Integer... value) {
        addEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public TrailerSelection movieVoteCountNot(Integer... value) {
        addNotEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public TrailerSelection movieVoteCountGt(int value) {
        addGreaterThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public TrailerSelection movieVoteCountGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public TrailerSelection movieVoteCountLt(int value) {
        addLessThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public TrailerSelection movieVoteCountLtEq(int value) {
        addLessThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public TrailerSelection orderByMovieVoteCount(boolean desc) {
        orderBy(MovieColumns.VOTE_COUNT, desc);
        return this;
    }

    public TrailerSelection orderByMovieVoteCount() {
        orderBy(MovieColumns.VOTE_COUNT, false);
        return this;
    }
}
