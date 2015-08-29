package com.franktan.popularmovies.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;

import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.util.PollingCheck;

import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by tan on 15/08/2015.
 */
public class DataTestUtilities {
    static final long TEST_DATE = 1435680000000L;  // 2015-07-01 GMT+8:00

    static ContentValues createMovieEntry() {
        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();
        testValues.put(MovieColumns.BACKDROP_PATH,   "/bIlYH4l2AyYvEysmS2AOfjO7Dn8.jpg");
        testValues.put(MovieColumns.MOVIE_MOVIEDB_ID,      "87101");
        testValues.put(MovieColumns.ORIGINAL_LAN,    "en");
        testValues.put(MovieColumns.ORIGINAL_TITLE,  "Terminator Genisys");
        testValues.put(MovieColumns.OVERVIEW,        "The year is 2029. John Connor, leader of the resistance " +
                "ontinues the war against the machines. At the Los Angeles offensive, John's fears of the unknown future begin to emerge when TECOM spies " +
                "reveal a new plot by SkyNet that will attack him from both fronts; past and future, and will ultimately change warfare forever.");
        testValues.put(MovieColumns.RELEASE_DATE,    TEST_DATE);
        testValues.put(MovieColumns.POSTER_PATH,     "/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg");
        testValues.put(MovieColumns.POPULARITY,      53.68);
        testValues.put(MovieColumns.TITLE,           "Terminator Genisys");
        testValues.put(MovieColumns.VOTE_AVERAGE,    6.3);
        testValues.put(MovieColumns.VOTE_COUNT,      713);

        return testValues;
    }

    static long insertMovieTestEntry(Context context) {
        // insert our test records into the database
        MovieSQLiteOpenHelper dbHelper = MovieSQLiteOpenHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues testValues = DataTestUtilities.createMovieEntry();

        long locationRowId;
        locationRowId = db.insert(MovieColumns.TABLE_NAME, null, testValues);

        // Verify we got a row back.
        assertTrue("Row Id of the movie record is returned and is not -1", locationRowId != -1);

        return locationRowId;
    }

    static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues){
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
//            Log.i(Constants.APP_NAME,"expected: "+expectedValue);
//            Log.i(Constants.APP_NAME,"actual: "+valueCursor.getString(idx));
            assertEquals("Value '" + entry.getValue().toString() +
                    "' should match the expected value '" +
                    expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
        }
    }

    public static void validateCursor(String error, Cursor valueCursor, ContentValues expectedValues) {
        assertTrue("Cursor should have records" + error, valueCursor.moveToFirst());
        validateCurrentRecord(error, valueCursor, expectedValues);
        valueCursor.close();
    }

    static TestContentObserver getTestContentObserver() {
        return TestContentObserver.getTestContentObserver();
    }

    static class TestContentObserver extends ContentObserver {
        final HandlerThread mHT;
        boolean mContentChanged;

        static TestContentObserver getTestContentObserver() {
            HandlerThread ht = new HandlerThread("ContentObserverThread");
            ht.start();
            return new TestContentObserver(ht);
        }

        private TestContentObserver(HandlerThread ht) {
            super(new Handler(ht.getLooper()));
            mHT = ht;
        }

        // On earlier versions of Android, this onChange method is called
        @Override
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            mContentChanged = true;
        }

        public void waitForNotificationOrFail() {
            // Note: The PollingCheck class is taken from the Android CTS (Compatibility Test Suite).
            // It's useful to look at the Android CTS source for ideas on how to test your Android
            // applications.  The reason that PollingCheck works is that, by default, the JUnit
            // testing framework is not running on the main Android application thread.
            new PollingCheck(5000) {
                @Override
                protected boolean check() {
                    return mContentChanged;
                }
            }.run();
            mHT.quit();
        }
    }
}
