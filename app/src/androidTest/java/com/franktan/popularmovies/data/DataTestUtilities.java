package com.franktan.popularmovies.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;

import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
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
    static final long TEST_DATE = 1435680000000L;  //  30 Jun 2015 16:00:00 GMT

    /**
     * Create a reference movie entry
     * @return a ContentValues with the movie entry data
     */
    static ContentValues createMovieEntry() {
        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();
        testValues.put(MovieColumns.BACKDROP_PATH,   "/tbhdm8UJAb4ViCTsulYFL3lxMCd.jpg");
        testValues.put(MovieColumns.MOVIE_MOVIEDB_ID,      76341);
        testValues.put(MovieColumns.ORIGINAL_LAN,    "en");
        testValues.put(MovieColumns.ORIGINAL_TITLE,  "Mad Max: Fury Road");
        testValues.put(MovieColumns.OVERVIEW,        "An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.");
        testValues.put(MovieColumns.RELEASE_DATE,    TEST_DATE);
        testValues.put(MovieColumns.POSTER_PATH,     "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg");
        testValues.put(MovieColumns.POPULARITY,      54.32);
        testValues.put(MovieColumns.TITLE,           "Mad Max: Fury Road");
        testValues.put(MovieColumns.VOTE_AVERAGE,    7.7);
        testValues.put(MovieColumns.VOTE_COUNT,      1978);

        return testValues;
    }

    static ContentValues createGenreEntry() {
        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();
        testValues.put(GenreColumns.GENRE_MOVIEDB_ID,   28);
        testValues.put(GenreColumns.NAME,               "Action");

        return testValues;
    }

    static ContentValues createMovieGenreEntry(long movieId, long genreId) {
        ContentValues testValues = new ContentValues();
        testValues.put(MovieGenreColumns.MOVIE_ID,   movieId);
        testValues.put(MovieGenreColumns.GENRE_ID,   genreId);

        return testValues;
    }

    /**
     * Use SQLiteOpenHelper to insert a movie record
     * @param context
     * @return
     */
    public static long insertMovieTestEntry(Context context) {
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

    /**
     * Compare the movie record from cursor with the reference data
     * @param error
     * @param valueCursor
     * @param expectedValues
     */
    static void validateMovieRecordUnderCursor(String error, Cursor valueCursor, ContentValues expectedValues){
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

    public static void validateMovieCursor(String error, Cursor valueCursor, ContentValues expectedValues) {
        assertTrue("Cursor should have records" + error, valueCursor.moveToFirst());
        validateMovieRecordUnderCursor(error, valueCursor, expectedValues);
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
