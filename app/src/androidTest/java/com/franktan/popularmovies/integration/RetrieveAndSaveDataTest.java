package com.franktan.popularmovies.integration;

import android.database.Cursor;
import android.test.AndroidTestCase;

import com.franktan.popularmovies.data.MovieContract;
import com.franktan.popularmovies.sync.MovieDbAPISyncService;
import com.franktan.popularmovies.sync.MovieSyncAdapter;

import org.mockito.Mockito;

/**
 * Created by tan on 16/08/2015.
 */
public class RetrieveAndSaveDataTest extends AndroidTestCase {
    // Test Retrieving data from Real Movie DB API
    public void testAPIReturn () {
        MovieDbAPISyncService movieDbAPISyncService = MovieDbAPISyncService.getDbSyncService();
        String jsonReturn = movieDbAPISyncService.getMovieInfoFromAPI("popularity", 1435708800L);
        assertNotNull("API should return something", jsonReturn);
        assertFalse("Returned Json should not be an empty string", jsonReturn.equals(""));
    }

    // Test retrieving fake json from a mock service, parsing it and save to DB
    public void testParsingAndSaving () {
        deleteAllRecordsFromProvider();
        MovieDbAPISyncService mockService = Mockito.mock(MovieDbAPISyncService.class);
        MovieSyncAdapter.syncMovieList();

        Cursor movieCursor = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        assertEquals("Should be 20 movie record in database", movieCursor.getCount());
        movieCursor.moveToFirst();
        assertEquals("First record should match the first movie in our list",
                102899, movieCursor.getInt(movieCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIEDB_ID)));

        movieCursor.moveToLast();
        assertEquals("Last record should match the last movie in our list",
                260346, movieCursor.getInt(movieCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIEDB_ID)));
        movieCursor.close();
    }

    public void deleteAllRecordsFromProvider() {
        mContext.getContentResolver().delete(
                MovieContract.MovieEntry.CONTENT_URI,
                null,
                null
        );

        Cursor cursor = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        assertEquals("Should be 0 record in table after delete", 0, cursor.getCount());
        cursor.close();

    }
}
