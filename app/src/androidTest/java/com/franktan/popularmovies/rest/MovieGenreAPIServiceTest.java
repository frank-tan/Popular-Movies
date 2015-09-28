package com.franktan.popularmovies.rest;

import android.test.AndroidTestCase;

import com.franktan.popularmovies.model.Genre;

import java.util.List;

/**
 * Created by tan on 20/09/2015.
 */
public class MovieGenreAPIServiceTest extends AndroidTestCase {

    public void testRetrieveMovieGenres() throws Exception {
        List<Genre> genres = MovieGenreAPIService.retrieveMovieGenres(mContext);
        assertNotNull("should return genre info",genres);

        for (Genre genre: genres) {
            assertTrue("genre element has a valid id", genre.getId() > 0);
            assertTrue("genre element has a valid name", genre.getName().length() > 0);
        }
    }
}