package com.franktan.popularmovies.service;

import android.content.Intent;
import android.test.ServiceTestCase;

import com.franktan.popularmovies.data.DataTestUtilities;
import com.franktan.popularmovies.data.movie.MovieCursor;
import com.franktan.popularmovies.data.movie.MovieSelection;
import com.franktan.popularmovies.data.review.ReviewCursor;
import com.franktan.popularmovies.data.review.ReviewSelection;
import com.franktan.popularmovies.data.trailer.TrailerCursor;
import com.franktan.popularmovies.data.trailer.TrailerSelection;
import com.franktan.popularmovies.util.Constants;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tan on 1/09/2015.
 */
public class MovieDetailsIntentServiceTest extends ServiceTestCase<MovieDetailsIntentServiceWrapper> {
    private CountDownLatch latch;

    public MovieDetailsIntentServiceTest () {
        super(MovieDetailsIntentServiceWrapper.class);
    }

    @Override
    protected void setupService() {
        super.setupService();

        latch = new CountDownLatch(1);
        getService().setLatch(latch);

        // insert the testing movie into movie table
        DataTestUtilities.insertMovieTestEntry(getSystemContext());
    }

    public void testIntentService() throws InterruptedException {
        // start intent service and wait for it to finish
        Intent intent = new Intent(getSystemContext(), MovieDetailsIntentService.class);
        intent.putExtra(Constants.MOVIEDB_ID, 76341);
        startService(intent);
        latch.await();

        // wait for intent service to finish
        MovieSelection movieSelection = new MovieSelection();
        movieSelection.movieMoviedbId(76341);

        MovieCursor movieCursor = movieSelection.query(getSystemContext().getContentResolver());
        movieCursor.moveToFirst();
        assertEquals("MovieDb Id should match", 76341, movieCursor.getMovieMoviedbId());
        assertTrue("Movie title should match", movieCursor.getTitle().equals("Mad Max: Fury Road"));
        long movieRowId = movieCursor.getId();
        movieCursor.close();

        checkReviewRecords(movieRowId);
        checkTrailerRecords(movieRowId);
    }

    void checkReviewRecords(long movieRowId) {
        // Check review record
        ReviewSelection reviewSelection = new ReviewSelection();
        reviewSelection.movieId(movieRowId);
        ReviewCursor reviewCursor = reviewSelection.query(getSystemContext().getContentResolver());

        assertTrue("should return some reviews", reviewCursor.getCount() > 0);

        reviewCursor.moveToFirst();
        assertTrue("author should not be empty", reviewCursor.getAuthor() != null && reviewCursor.getAuthor().length() > 0);
        assertTrue("content should not be empty", reviewCursor.getContent() != null && reviewCursor.getContent().length() > 0);
        assertTrue("url should not be empty", reviewCursor.getUrl() != null && reviewCursor.getUrl().length() > 0);
        reviewCursor.close();
    }

    void checkTrailerRecords(long movieRowId) {
        // Check trailers record
        TrailerSelection trailerSelection = new TrailerSelection();
        trailerSelection.movieId(movieRowId);
        TrailerCursor trailerCursor = trailerSelection.query(getSystemContext().getContentResolver());

        assertTrue("should return some trailers", trailerCursor.getCount() > 0);

        trailerCursor.moveToFirst();
        assertTrue("name should not be empty", trailerCursor.getName() != null && trailerCursor.getName().length() > 0);
        assertTrue("size should not be empty",trailerCursor.getSize() != null && trailerCursor.getSize().length() > 0);
        assertTrue("source should not be empty",trailerCursor.getSource() != null && trailerCursor.getSource().length() > 0);
        assertTrue("type should not be empty",trailerCursor.getType() != null && trailerCursor.getType().length() > 0);
        trailerCursor.close();
    }
}
