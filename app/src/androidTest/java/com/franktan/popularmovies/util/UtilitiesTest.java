package com.franktan.popularmovies.util;

import android.test.AndroidTestCase;

/**
 * Created by tan on 29/08/2015.
 */
public class UtilitiesTest extends AndroidTestCase {
    /**
     * Test API key can be retrieved from environment variable
     * (For CI only)
     */
    public void testGetMovieDBApiKey () {
        String apiKey = Utilities.getMovieDBApiKey(mContext);
        assertTrue("MovieDB API Key should not be null", apiKey.length() > 0);
    }

    public void testGetGoogleApiKey() throws Exception {
        String apiKey = Utilities.getMovieDBApiKey(mContext);
        assertTrue("Google API Key should not be null", apiKey.length() > 0);
    }
}
