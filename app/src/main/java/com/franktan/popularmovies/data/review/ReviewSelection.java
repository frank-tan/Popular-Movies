package com.franktan.popularmovies.data.review;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.franktan.popularmovies.data.base.AbstractSelection;
import com.franktan.popularmovies.data.movie.*;

/**
 * Selection for the {@code review} table.
 */
public class ReviewSelection extends AbstractSelection<ReviewSelection> {
    @Override
    protected Uri baseUri() {
        return ReviewColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ReviewCursor} object, which is positioned before the first entry, or null.
     */
    public ReviewCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ReviewCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ReviewCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ReviewCursor} object, which is positioned before the first entry, or null.
     */
    public ReviewCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ReviewCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ReviewCursor query(Context context) {
        return query(context, null);
    }


    public ReviewSelection id(long... value) {
        addEquals("review." + ReviewColumns._ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection idNot(long... value) {
        addNotEquals("review." + ReviewColumns._ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection orderById(boolean desc) {
        orderBy("review." + ReviewColumns._ID, desc);
        return this;
    }

    public ReviewSelection orderById() {
        return orderById(false);
    }

    public ReviewSelection reviewMoviedbId(int... value) {
        addEquals(ReviewColumns.REVIEW_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection reviewMoviedbIdNot(int... value) {
        addNotEquals(ReviewColumns.REVIEW_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection reviewMoviedbIdGt(int value) {
        addGreaterThan(ReviewColumns.REVIEW_MOVIEDB_ID, value);
        return this;
    }

    public ReviewSelection reviewMoviedbIdGtEq(int value) {
        addGreaterThanOrEquals(ReviewColumns.REVIEW_MOVIEDB_ID, value);
        return this;
    }

    public ReviewSelection reviewMoviedbIdLt(int value) {
        addLessThan(ReviewColumns.REVIEW_MOVIEDB_ID, value);
        return this;
    }

    public ReviewSelection reviewMoviedbIdLtEq(int value) {
        addLessThanOrEquals(ReviewColumns.REVIEW_MOVIEDB_ID, value);
        return this;
    }

    public ReviewSelection orderByReviewMoviedbId(boolean desc) {
        orderBy(ReviewColumns.REVIEW_MOVIEDB_ID, desc);
        return this;
    }

    public ReviewSelection orderByReviewMoviedbId() {
        orderBy(ReviewColumns.REVIEW_MOVIEDB_ID, false);
        return this;
    }

    public ReviewSelection author(String... value) {
        addEquals(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorNot(String... value) {
        addNotEquals(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorLike(String... value) {
        addLike(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorContains(String... value) {
        addContains(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorStartsWith(String... value) {
        addStartsWith(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorEndsWith(String... value) {
        addEndsWith(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection orderByAuthor(boolean desc) {
        orderBy(ReviewColumns.AUTHOR, desc);
        return this;
    }

    public ReviewSelection orderByAuthor() {
        orderBy(ReviewColumns.AUTHOR, false);
        return this;
    }

    public ReviewSelection content(String... value) {
        addEquals(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentNot(String... value) {
        addNotEquals(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentLike(String... value) {
        addLike(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentContains(String... value) {
        addContains(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentStartsWith(String... value) {
        addStartsWith(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentEndsWith(String... value) {
        addEndsWith(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection orderByContent(boolean desc) {
        orderBy(ReviewColumns.CONTENT, desc);
        return this;
    }

    public ReviewSelection orderByContent() {
        orderBy(ReviewColumns.CONTENT, false);
        return this;
    }

    public ReviewSelection url(String... value) {
        addEquals(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection urlNot(String... value) {
        addNotEquals(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection urlLike(String... value) {
        addLike(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection urlContains(String... value) {
        addContains(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection urlStartsWith(String... value) {
        addStartsWith(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection urlEndsWith(String... value) {
        addEndsWith(ReviewColumns.URL, value);
        return this;
    }

    public ReviewSelection orderByUrl(boolean desc) {
        orderBy(ReviewColumns.URL, desc);
        return this;
    }

    public ReviewSelection orderByUrl() {
        orderBy(ReviewColumns.URL, false);
        return this;
    }

    public ReviewSelection movieId(long... value) {
        addEquals(ReviewColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieIdNot(long... value) {
        addNotEquals(ReviewColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieIdGt(long value) {
        addGreaterThan(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieIdLt(long value) {
        addLessThan(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieIdLtEq(long value) {
        addLessThanOrEquals(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection orderByMovieId(boolean desc) {
        orderBy(ReviewColumns.MOVIE_ID, desc);
        return this;
    }

    public ReviewSelection orderByMovieId() {
        orderBy(ReviewColumns.MOVIE_ID, false);
        return this;
    }

    public ReviewSelection movieMovieMoviedbId(long... value) {
        addEquals(MovieColumns.MOVIE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieMovieMoviedbIdNot(long... value) {
        addNotEquals(MovieColumns.MOVIE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieMovieMoviedbIdGt(long value) {
        addGreaterThan(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public ReviewSelection movieMovieMoviedbIdGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public ReviewSelection movieMovieMoviedbIdLt(long value) {
        addLessThan(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public ReviewSelection movieMovieMoviedbIdLtEq(long value) {
        addLessThanOrEquals(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public ReviewSelection orderByMovieMovieMoviedbId(boolean desc) {
        orderBy(MovieColumns.MOVIE_MOVIEDB_ID, desc);
        return this;
    }

    public ReviewSelection orderByMovieMovieMoviedbId() {
        orderBy(MovieColumns.MOVIE_MOVIEDB_ID, false);
        return this;
    }

    public ReviewSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public ReviewSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public ReviewSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public ReviewSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public ReviewSelection movieOriginalLan(String... value) {
        addEquals(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public ReviewSelection movieOriginalLanNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public ReviewSelection movieOriginalLanLike(String... value) {
        addLike(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public ReviewSelection movieOriginalLanContains(String... value) {
        addContains(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public ReviewSelection movieOriginalLanStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public ReviewSelection movieOriginalLanEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public ReviewSelection orderByMovieOriginalLan(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_LAN, desc);
        return this;
    }

    public ReviewSelection orderByMovieOriginalLan() {
        orderBy(MovieColumns.ORIGINAL_LAN, false);
        return this;
    }

    public ReviewSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public ReviewSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public ReviewSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public ReviewSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public ReviewSelection movieReleaseDate(Long... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateNot(Long... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateGt(long value) {
        addGreaterThan(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateLt(long value) {
        addLessThan(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateLtEq(long value) {
        addLessThanOrEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public ReviewSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public ReviewSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public ReviewSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public ReviewSelection moviePopularity(Double... value) {
        addEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public ReviewSelection moviePopularityNot(Double... value) {
        addNotEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public ReviewSelection moviePopularityGt(double value) {
        addGreaterThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public ReviewSelection moviePopularityGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public ReviewSelection moviePopularityLt(double value) {
        addLessThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public ReviewSelection moviePopularityLtEq(double value) {
        addLessThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public ReviewSelection orderByMoviePopularity(boolean desc) {
        orderBy(MovieColumns.POPULARITY, desc);
        return this;
    }

    public ReviewSelection orderByMoviePopularity() {
        orderBy(MovieColumns.POPULARITY, false);
        return this;
    }

    public ReviewSelection movieVoteAverage(Double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageNot(Double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public ReviewSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public ReviewSelection movieVoteCount(Integer... value) {
        addEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public ReviewSelection movieVoteCountNot(Integer... value) {
        addNotEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public ReviewSelection movieVoteCountGt(int value) {
        addGreaterThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public ReviewSelection movieVoteCountGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public ReviewSelection movieVoteCountLt(int value) {
        addLessThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public ReviewSelection movieVoteCountLtEq(int value) {
        addLessThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public ReviewSelection orderByMovieVoteCount(boolean desc) {
        orderBy(MovieColumns.VOTE_COUNT, desc);
        return this;
    }

    public ReviewSelection orderByMovieVoteCount() {
        orderBy(MovieColumns.VOTE_COUNT, false);
        return this;
    }
}
