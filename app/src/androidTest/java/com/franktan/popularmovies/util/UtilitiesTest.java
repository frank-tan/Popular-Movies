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
    public void testApiKey () {
        String apiKey = Utilities.getApiKey(mContext);
        assertTrue("API Key should not be null", (apiKey != null && !apiKey.equals("")));
    }
}
