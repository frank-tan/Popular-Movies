package com.franktan.popularmovies.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.movie.MovieCursor;
import com.franktan.popularmovies.data.movie.MovieSelection;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.review.ReviewContentValues;
import com.franktan.popularmovies.data.trailer.TrailerColumns;
import com.franktan.popularmovies.data.trailer.TrailerContentValues;
import com.franktan.popularmovies.model.Movie;
import com.franktan.popularmovies.model.Review;
import com.franktan.popularmovies.model.Trailer;
import com.franktan.popularmovies.rest.MovieDetailsAPIService;
import com.franktan.popularmovies.util.Constants;
import com.franktan.popularmovies.util.Utilities;

/**
 * Created by tan on 29/08/2015.
 * Intent service to retrieve and save trailers and reviews of a movie
 */
public class MovieDetailsIntentService extends IntentService {
    public MovieDetailsIntentService() {
        super("MovieDetailsIntentService");
    }
    public MovieDetailsIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(!Utilities.isNetworkAvailable(getApplicationContext()))
            return;
        long movieMovieDBId = intent.getLongExtra(Constants.MOVIEDB_ID, -1);
        if(movieMovieDBId == -1) return;

        Movie movie;
        try {
            movie = MovieDetailsAPIService.retrieveMovieDetails(this, movieMovieDBId, null);
        } catch (retrofit.RetrofitError e) {
            Log.w(Constants.APP_NAME,"Retrieving movie details REST call failed with " + e.toString());
            return;
        }

        MovieSelection movieSelection = new MovieSelection();
        movieSelection.movieMoviedbId(movieMovieDBId);
        MovieCursor movieCursor = movieSelection.query(getContentResolver());
        if(!movieCursor.moveToFirst()) {
            Log.e(Constants.APP_NAME,"MovieDetailsIntentService: cannot find movie in database. MovieDB id: " + movieMovieDBId);
            movieCursor.close();
            return;
        }
        long movieRowId = movieCursor.getId();
        movieCursor.close();

        int reviewsInserted = insertReviews(movie, movieRowId);
        int trailersInserted = insertTrailers(movie, movieRowId);

        // if new reviews or trailers are inserted into database notify cursor loader
        if(reviewsInserted + trailersInserted > 0) {
            getApplicationContext().getContentResolver().notifyChange(Uri.withAppendedPath(MovieColumns.CONTENT_URI, "moviedb/" + String.valueOf(movieMovieDBId)), null);
        }
    }

    private int insertReviews(Movie movie, long movieRowId) {

        ContentValues[] reviews = new ContentValues[movie.getReviews().size()];

        for (int i = 0; i < movie.getReviews().size(); i++) {
            Review review = movie.getReviews().get(i);

            ReviewContentValues reviewContentValues = new ReviewContentValues();
            reviewContentValues.putAuthor(review.getAuthor());
            reviewContentValues.putMovieId(movieRowId);
            reviewContentValues.putContent(review.getContent());
            //TODO: review id should be String
            reviewContentValues.putReviewMoviedbId(0);
            reviewContentValues.putUrl(review.getUrl());

            reviews[i] = reviewContentValues.values();
        }
        return getContentResolver().bulkInsert(ReviewColumns.CONTENT_URI, reviews);
    }

    private int insertTrailers(Movie movie, long movieRowId) {
        ContentValues[] trailers = new ContentValues[movie.getTrailers().size()];

        for (int i = 0; i < movie.getTrailers().size(); i++) {
            Trailer trailer = movie.getTrailers().get(i);

            TrailerContentValues trailerContentValues = new TrailerContentValues();
            trailerContentValues.putMovieId(movieRowId);
            trailerContentValues.putName(trailer.getName());
            trailerContentValues.putSize(trailer.getSize());
            trailerContentValues.putSource(trailer.getSource());
            trailerContentValues.putType(trailer.getType());

            trailers[i] = trailerContentValues.values();
        }
        return getContentResolver().bulkInsert(TrailerColumns.CONTENT_URI, trailers);
    }
}
