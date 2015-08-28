package com.franktan.popularmovies.util;

import android.content.res.Resources;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.franktan.popularmovies.model.Movie;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

/**
 * Created by tan on 16/08/2015.
 */
public class ParserTest extends InstrumentationTestCase {
    static final long TEST_DATE = 1435708800000L;  // 2015-07-01 GMT

    /**
     * Test parseJson method can correctly parse a typical movie json string
     */
    public void testParseJson () throws IOException, JSONException, ParseException {
        List<Movie> movies = Parser.parseJson(getTestingMovieJson());
        assertNotNull("json string should be parsed and not returns null", movies);
        Log.i("popularmovies", movies.get(0).toString());
        Log.i("popularmovies", TestingUtilities.createMovieNo1().toString());
        assertTrue("1st movie should match", movies.get(0).equals(TestingUtilities.createMovieNo1()));
        assertTrue("20th movie should match", movies.get(19).equals(TestingUtilities.createMovieNo20()));
    }

    //TODO write test for contentValuesFromMovieList method

    public void testHumanDateStringFromMiliseconds() throws Exception {
        String result = Parser.humanDateStringFromMiliseconds(TEST_DATE);
        assertTrue("Should be able to parse epoch miliseconds to human friendly format: result "+ result, result.equals("1 Jul 2015"));
    }

    public void testEpochFromMovieDbDateString() throws Exception {
        long result = Parser.epochFromMovieDbDateString("2015-07-01");
        assertEquals("Should be able to parse moviedb date", TEST_DATE, result);
    }

    public void testMovieDbDateStringFromMiliseconds() throws Exception {
        String result = Parser.movieDbDateStringFromMiliseconds(TEST_DATE);
        assertTrue("Should be able to parse epoch miliseconds to moviedb format: result "+ result, result.equals("2015-07-01"));
    }

    public String getTestingMovieJson() throws IOException {

        Resources res = getInstrumentation().getContext().getResources();
        InputStream in = res.getAssets().open("mock_movies.json");
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

}
