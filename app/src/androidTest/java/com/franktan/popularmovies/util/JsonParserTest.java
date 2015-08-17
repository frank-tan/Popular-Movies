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
public class JsonParserTest extends InstrumentationTestCase {
    /**
     * Test parseJson method can correctly parse a typical movie json string
     */
    public void testParsingMovieJson () throws IOException, JSONException, ParseException {
        List<Movie> movies = JsonParser.parseJson(getTestingMovieJson());
        assertNotNull("json string should be parsed and not returns null", movies);
        Log.i("popularmovies", movies.get(0).toString());
        Log.i("popularmovies", SyncTestUtilities.createMovieNo1().toString());
        assertTrue("1st movie should match", movies.get(0).equals(SyncTestUtilities.createMovieNo1()));
        assertTrue("20th movie should match", movies.get(19).equals(SyncTestUtilities.createMovieNo20()));
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
