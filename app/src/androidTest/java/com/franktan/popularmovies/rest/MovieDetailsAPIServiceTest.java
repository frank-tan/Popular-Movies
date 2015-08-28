package com.franktan.popularmovies.rest;

import android.content.res.Resources;
import android.net.Uri;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.franktan.popularmovies.model.Movie;
import com.franktan.popularmovies.util.Constants;
import com.franktan.popularmovies.util.SyncTestUtilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by tan on 28/08/2015.
 */
public class MovieDetailsAPIServiceTest extends InstrumentationTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Test retrieveMovieDetails returns correct Movie object providing a mock json string
     */
    public void testRetrieveMovieDetails () {
        // create a RestAdapter Builder using static method
        RestAdapter.Builder restAdapterBuilder = MovieDetailsAPIService.createRestAdapterBuilder(MovieDetailsAPIService.getGson());

        // set the mock client
        restAdapterBuilder.setClient(new MockClient());

        // create RestAdapter from the builder
        RestAdapter restAdapter = restAdapterBuilder.build();

        // create service from RestAdapter
        MovieDetailsAPIService.MovieDetailsAPI service = restAdapter.create(MovieDetailsAPIService.MovieDetailsAPI.class);

        // call the api
        Movie movie = service.retrieveMovieDetails(76341);

        assertTrue("Should be able to parse mock movie details json", movie.equals(SyncTestUtilities.createMovieDetails()));

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
            Uri uri = Uri.parse(request.getUrl());

            Log.d(Constants.APP_NAME, "Mock server fetching uri: " + uri.toString());

            String responseString = getTestingMovieDetailsJson();

            return new Response(request.getUrl(), 200, "nothing", Collections.EMPTY_LIST, new TypedByteArray("application/json", responseString.getBytes()));
        }
    }
}
