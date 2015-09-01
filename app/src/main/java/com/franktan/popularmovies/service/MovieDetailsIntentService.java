package com.franktan.popularmovies.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;

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

/**
 * Created by tan on 29/08/2015.
 */
public class MovieDetailsIntentService extends IntentService {
    public MovieDetailsIntentService() {
        super("MovieDetailsIntentService");
    }
    public MovieDetailsIntentService(String name) {
        super(name);
    }

    //// TODO: 31/08/2015 Added unit test for this service
    @Override
    protected void onHandleIntent(Intent intent) {
        int movieMovieDBId = intent.getIntExtra(Constants.MOVIEDB_ID, -1);
        if(movieMovieDBId == -1) return;

        Movie movie = MovieDetailsAPIService.retrieveMovieDetails(this, movieMovieDBId, null);

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

        insertReviews(movie, movieRowId);
        insertTrailers(movie, movieRowId);
    }

    private void insertReviews(Movie movie, long movieRowId) {
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
        int reviewNum = getContentResolver().bulkInsert(ReviewColumns.CONTENT_URI, reviews);
        Log.i(Constants.APP_NAME, "number of reviews inserted: " + reviewNum);
    }

    private void insertTrailers(Movie movie, long movieRowId) {
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
        int reviewNum = getContentResolver().bulkInsert(TrailerColumns.CONTENT_URI, trailers);
        Log.i(Constants.APP_NAME,"number of trailers inserted: " + reviewNum);
    }

    //TODO: insertGenres
}
