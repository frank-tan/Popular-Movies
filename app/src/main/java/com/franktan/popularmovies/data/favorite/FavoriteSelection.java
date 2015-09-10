package com.franktan.popularmovies.data.favorite;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.franktan.popularmovies.data.base.AbstractSelection;
import com.franktan.popularmovies.data.movie.*;

/**
 * Selection for the {@code favorite} table.
 */
public class FavoriteSelection extends AbstractSelection<FavoriteSelection> {
    @Override
    protected Uri baseUri() {
        return FavoriteColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoriteCursor} object, which is positioned before the first entry, or null.
     */
    public FavoriteCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoriteCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public FavoriteCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoriteCursor} object, which is positioned before the first entry, or null.
     */
    public FavoriteCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoriteCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public FavoriteCursor query(Context context) {
        return query(context, null);
    }


    public FavoriteSelection id(long... value) {
        addEquals("favorite." + FavoriteColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection idNot(long... value) {
        addNotEquals("favorite." + FavoriteColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection orderById(boolean desc) {
        orderBy("favorite." + FavoriteColumns._ID, desc);
        return this;
    }

    public FavoriteSelection orderById() {
        return orderById(false);
    }

    public FavoriteSelection favoriteMoviedbId(long... value) {
        addEquals(FavoriteColumns.FAVORITE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection favoriteMoviedbIdNot(long... value) {
        addNotEquals(FavoriteColumns.FAVORITE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection favoriteMoviedbIdGt(long value) {
        addGreaterThan(FavoriteColumns.FAVORITE_MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection favoriteMoviedbIdGtEq(long value) {
        addGreaterThanOrEquals(FavoriteColumns.FAVORITE_MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection favoriteMoviedbIdLt(long value) {
        addLessThan(FavoriteColumns.FAVORITE_MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection favoriteMoviedbIdLtEq(long value) {
        addLessThanOrEquals(FavoriteColumns.FAVORITE_MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection orderByFavoriteMoviedbId(boolean desc) {
        orderBy(FavoriteColumns.FAVORITE_MOVIEDB_ID, desc);
        return this;
    }

    public FavoriteSelection orderByFavoriteMoviedbId() {
        orderBy(FavoriteColumns.FAVORITE_MOVIEDB_ID, false);
        return this;
    }

    public FavoriteSelection movieMovieMoviedbId(long... value) {
        addEquals(MovieColumns.MOVIE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection movieMovieMoviedbIdNot(long... value) {
        addNotEquals(MovieColumns.MOVIE_MOVIEDB_ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection movieMovieMoviedbIdGt(long value) {
        addGreaterThan(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection movieMovieMoviedbIdGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection movieMovieMoviedbIdLt(long value) {
        addLessThan(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection movieMovieMoviedbIdLtEq(long value) {
        addLessThanOrEquals(MovieColumns.MOVIE_MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection orderByMovieMovieMoviedbId(boolean desc) {
        orderBy(MovieColumns.MOVIE_MOVIEDB_ID, desc);
        return this;
    }

    public FavoriteSelection orderByMovieMovieMoviedbId() {
        orderBy(MovieColumns.MOVIE_MOVIEDB_ID, false);
        return this;
    }

    public FavoriteSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public FavoriteSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public FavoriteSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public FavoriteSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public FavoriteSelection movieOriginalLan(String... value) {
        addEquals(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public FavoriteSelection movieOriginalLanNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public FavoriteSelection movieOriginalLanLike(String... value) {
        addLike(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public FavoriteSelection movieOriginalLanContains(String... value) {
        addContains(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public FavoriteSelection movieOriginalLanStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public FavoriteSelection movieOriginalLanEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_LAN, value);
        return this;
    }

    public FavoriteSelection orderByMovieOriginalLan(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_LAN, desc);
        return this;
    }

    public FavoriteSelection orderByMovieOriginalLan() {
        orderBy(MovieColumns.ORIGINAL_LAN, false);
        return this;
    }

    public FavoriteSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public FavoriteSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public FavoriteSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public FavoriteSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public FavoriteSelection movieReleaseDate(Long... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection movieReleaseDateNot(Long... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection movieReleaseDateGt(long value) {
        addGreaterThan(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection movieReleaseDateGtEq(long value) {
        addGreaterThanOrEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection movieReleaseDateLt(long value) {
        addLessThan(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection movieReleaseDateLtEq(long value) {
        addLessThanOrEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public FavoriteSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public FavoriteSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public FavoriteSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public FavoriteSelection moviePopularity(Double... value) {
        addEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection moviePopularityNot(Double... value) {
        addNotEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection moviePopularityGt(double value) {
        addGreaterThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection moviePopularityGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection moviePopularityLt(double value) {
        addLessThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection moviePopularityLtEq(double value) {
        addLessThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection orderByMoviePopularity(boolean desc) {
        orderBy(MovieColumns.POPULARITY, desc);
        return this;
    }

    public FavoriteSelection orderByMoviePopularity() {
        orderBy(MovieColumns.POPULARITY, false);
        return this;
    }

    public FavoriteSelection movieVoteAverage(Double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection movieVoteAverageNot(Double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection movieVoteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection movieVoteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection movieVoteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection movieVoteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public FavoriteSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public FavoriteSelection movieVoteCount(Integer... value) {
        addEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection movieVoteCountNot(Integer... value) {
        addNotEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection movieVoteCountGt(int value) {
        addGreaterThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection movieVoteCountGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection movieVoteCountLt(int value) {
        addLessThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection movieVoteCountLtEq(int value) {
        addLessThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection orderByMovieVoteCount(boolean desc) {
        orderBy(MovieColumns.VOTE_COUNT, desc);
        return this;
    }

    public FavoriteSelection orderByMovieVoteCount() {
        orderBy(MovieColumns.VOTE_COUNT, false);
        return this;
    }

    public FavoriteSelection created(Long... value) {
        addEquals(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection createdNot(Long... value) {
        addNotEquals(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection createdGt(long value) {
        addGreaterThan(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection createdGtEq(long value) {
        addGreaterThanOrEquals(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection createdLt(long value) {
        addLessThan(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection createdLtEq(long value) {
        addLessThanOrEquals(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection orderByCreated(boolean desc) {
        orderBy(FavoriteColumns.CREATED, desc);
        return this;
    }

    public FavoriteSelection orderByCreated() {
        orderBy(FavoriteColumns.CREATED, false);
        return this;
    }
}
