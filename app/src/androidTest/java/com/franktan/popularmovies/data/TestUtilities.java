package com.franktan.popularmovies.data;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.*;

/**
 * Created by tan on 15/08/2015.
 */
public class TestUtilities {
    static final long TEST_DATE = 1435708800L;  // 2015-07-01

    public static ContentValues createMovieEntry() {
        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();
        testValues.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH,   "/bIlYH4l2AyYvEysmS2AOfjO7Dn8.jpg");
        testValues.put(MovieContract.MovieEntry.COLUMN_MOVIEDB_ID,      "87101");
        testValues.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_LAN,    "en");
        testValues.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE,  "Terminator Genisys");
        testValues.put(MovieContract.MovieEntry.COLUMN_OVERVIEW,        "The year is 2029. John Connor, leader of the resistance " +
                "ontinues the war against the machines. At the Los Angeles offensive, John's fears of the unknown future begin to emerge when TECOM spies " +
                "reveal a new plot by SkyNet that will attack him from both fronts; past and future, and will ultimately change warfare forever.");
        testValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE,    TEST_DATE);
        testValues.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH,     "/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg");
        testValues.put(MovieContract.MovieEntry.COLUMN_POPULARITY,      53.68);
        testValues.put(MovieContract.MovieEntry.COLUMN_TITLE,           "Terminator Genisys");
        testValues.put(MovieContract.MovieEntry.COLUMN_VIDEO,           false);
        testValues.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE,    6.3);
        testValues.put(MovieContract.MovieEntry.COLUMN_VOTE_COUNT,      713);

        return testValues;
    }

    public static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues){
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' should exist. " + error, idx == -1);
            String expectedValue = entry.getValue().toString();
            if(expectedValue.equals("false")){
                expectedValue = "0";
            } else if(expectedValue.equals("true")){
                expectedValue = "1";
            }
            assertEquals("Value '" + entry.getValue().toString() +
                    "' should match the expected value '" +
                    expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
        }
    }
}
