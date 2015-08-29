package com.franktan.popularmovies.rest;

import android.content.res.Resources;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.franktan.popularmovies.model.Movie;
import com.franktan.popularmovies.util.Constants;
import com.franktan.popularmovies.util.TestingUtilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by tan on 28/08/2015.
 */
public class MovieDetailsAPIServiceTest extends InstrumentationTestCase {
    private static final String MOVIE_DETAILS_URL = "https://api.themoviedb.org/3/movie/76341?api_key=7c3904f53f503110b7c4be7fe34fd416&append_to_response=reviews,trailers";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Test retrieveMovieDetails returns correct Movie object providing a mock json string
     */
    public void testRetrieveMovieDetails () {
        // call the api with a mock client
        Movie movie = MovieDetailsAPIService.retrieveMovieDetails(getInstrumentation().getTargetContext(), 76341, new MockClient());

        assertTrue("Should be able to parse mock movie details json", movie.equals(TestingUtilities.createMovieDetails()));

    }

    /**
     * Read the movie detail json file and return the json string
     * @return mock movie detail json string
     * @throws IOException
     */
    public String getTestingMovieDetailsJson() throws IOException {
        Resources res = getInstrumentation().getContext().getResources();
        InputStream in = res.getAssets().open("mock_movie_details.json");
        byte[] b  = new byte[(int) in.available()];
        int len = b.length;
        int total = 0;

        while (total < len) {
            int result = in.read(b, total, len - total);
            if (result == -1) {
                break;
            }
            total += result;
        }
        return new String(b);
    }

    /**
     * A mock client which mocks MovieDB movie Details return
     */
    public class MockClient implements Client {

        @Override
        public Response execute(Request request) throws IOException {
            URL url = new URL(request.getUrl());

            Log.d(Constants.APP_NAME, "Mock server fetching URL: " + url.getPath());

            String responseString = getTestingMovieDetailsJson();

            return new Response(request.getUrl(), 200, "nothing", Collections.EMPTY_LIST, new TypedByteArray("application/json", responseString.getBytes()));
        }
    }
}
