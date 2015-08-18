package com.franktan.popularmovies.data;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

/**
 * Created by tan on 15/08/2015.
 */
public class MovieProviderTest extends AndroidTestCase {

    static private final int BULK_INSERT_RECORDS_TO_INSERT = 10;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        deleteAllRecords();
    }

    public void testProviderRegistry() {
        PackageManager pm = mContext.getPackageManager();

        // We define the component name based on the package name from the context and the
        // WeatherProvider class.
        ComponentName componentName = new ComponentName(mContext.getPackageName(),
                MovieProvider.class.getName());
        try {
            // Fetch the provider info using the component name from the PackageManager
            // This throws an exception if the provider isn't registered.
            ProviderInfo providerInfo = pm.getProviderInfo(componentName, 0);

            // Make sure that the registered authority matches the authority from the Contract.
            assertEquals("Error: MovieProvider registered with authority: " + providerInfo.authority +
                            " instead of authority: " + MovieContract.CONTENT_AUTHORITY,
                    providerInfo.authority, MovieContract.CONTENT_AUTHORITY);
        } catch (PackageManager.NameNotFoundException e) {
            // I guess the provider isn't registered correctly.
            assertTrue("Error: MovieProvider not registered at " + mContext.getPackageName(),
                    false);
        }
    }

    public void testProviderGetType() {
        // content://com.example.android.sunshine.app/weather/
        String type = mContext.getContentResolver().getType(MovieContract.MovieEntry.CONTENT_URI);
        // vnd.android.cursor.dir/com.example.android.sunshine.app/weather
        assertEquals("the MovieEntry CONTENT_URI should return MovieEntry.CONTENT_TYPE",
                MovieContract.MovieEntry.CONTENT_TYPE, type);

        int id = 1;
        // content://com.franktan.popularmovie/movie/1
        type = mContext.getContentResolver().getType(
                MovieContract.MovieEntry.buildMovieUri(id));
        // vnd.android.cursor.item/com.franktan.popularmovie/movie/1
        assertEquals("Movie uri content type should be MovieEntry.CONTENT_TYPE",
                MovieContract.MovieEntry.CONTENT_ITEM_TYPE, type);

    }

    /**
     * Insert using direct SQLiteDbHelper and test query using content provider
     */
    public void testProviderQuery() {

        ContentValues testValues = DataTestUtilities.createMovieEntry();
        long movieRowId = DataTestUtilities.insertMovieTestEntry(mContext);

        // Test the basic content provider query
        Cursor movieCursor = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        // Make sure we get the correct cursor out of the database
        DataTestUtilities.validateCursor("testBasicWeatherQuery", movieCursor, DataTestUtilities.createMovieEntry());

        movieCursor.close();

        //Test query by Id
        Cursor movieCursorById = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                null,
                MovieContract.MovieEntry._ID + " = " + movieRowId,
                null,
                null
        );
        movieCursorById.close();
    }

    /**
     * Test insert using content provider
     */
    public void testProviderInsert() {
        ContentValues testValues = DataTestUtilities.createMovieEntry();

        // Register a content observer for our insert.  This time, directly with the content resolver
        DataTestUtilities.TestContentObserver tco = DataTestUtilities.getTestContentObserver();
        mContext.getContentResolver().registerContentObserver(MovieContract.MovieEntry.CONTENT_URI, true, tco);
        Uri movieUri = mContext.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, testValues);

        tco.waitForNotificationOrFail();
        mContext.getContentResolver().unregisterContentObserver(tco);

        long movieRowId = ContentUris.parseId(movieUri);

        // Verify we got a row back.
        assertTrue("Valid row id should be returned from content provider insert", movieRowId != -1);

        Cursor cursor = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                null, // leaving "columns" null just returns all the columns.
                null, // cols for "where" clause
                null, // values for "where" clause
                null  // sort order
        );

        DataTestUtilities.validateCursor("Movie record should have been inserted in the table",
                cursor, testValues);

        cursor.close();
    }

    public void testProviderUpdate() {
        // Create a new map of values, where column names are the keys
        ContentValues values = DataTestUtilities.createMovieEntry();

        Uri movieUri = mContext.getContentResolver().
                insert(MovieContract.MovieEntry.CONTENT_URI, values);
        long movieRowId = ContentUris.parseId(movieUri);

        // Verify we got a row back.
        assertTrue("Should be able to insert a record and get back a valid row id", movieRowId != -1);

        ContentValues updatedValues = new ContentValues(values);
        updatedValues.put(MovieContract.MovieEntry._ID, movieRowId);
        updatedValues.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_LAN, "Aussie English");

        // Create a cursor with observer to make sure that the content provider is notifying
        // the observers as expected
        Cursor movieCursor = mContext.getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI, null, null, null, null);

        DataTestUtilities.TestContentObserver tco = DataTestUtilities.getTestContentObserver();
        movieCursor.registerContentObserver(tco);

        int count = mContext.getContentResolver().update(
                MovieContract.MovieEntry.CONTENT_URI, updatedValues, MovieContract.MovieEntry._ID + "= ?",
                new String[] { Long.toString(movieRowId)});
        assertEquals("Number of records updated should be 1", 1, count);

        // Test to make sure our observer is called.  If not, we throw an assertion.
        tco.waitForNotificationOrFail();

        movieCursor.unregisterContentObserver(tco);
        movieCursor.close();

        // A cursor is your primary interface to the query results.
        Cursor cursor = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                null,   // projection
                MovieContract.MovieEntry._ID + " = " + movieRowId,
                null,   // Values for the "where" clause
                null    // sort order
        );

        DataTestUtilities.validateCursor("The record in the database should have been updated",
                cursor, updatedValues);

        cursor.close();
    }

    public void testProviderDelete() {
        testProviderInsert();

        DataTestUtilities.TestContentObserver locationObserver = DataTestUtilities.getTestContentObserver();
        mContext.getContentResolver().registerContentObserver(MovieContract.MovieEntry.CONTENT_URI, true, locationObserver);

        deleteAllRecordsFromProvider();

        locationObserver.waitForNotificationOrFail();

        mContext.getContentResolver().unregisterContentObserver(locationObserver);
    }

    public void testProviderBulkInsert() {

        ContentValues[] bulkInsertContentValues = createBulkInsertMovieValues();

        // Register a content observer for our bulk insert.
        DataTestUtilities.TestContentObserver weatherObserver = DataTestUtilities.getTestContentObserver();
        mContext.getContentResolver().registerContentObserver(MovieContract.MovieEntry.CONTENT_URI, true, weatherObserver);

        int insertCount = mContext.getContentResolver().bulkInsert(MovieContract.MovieEntry.CONTENT_URI, bulkInsertContentValues);

        weatherObserver.waitForNotificationOrFail();
        mContext.getContentResolver().unregisterContentObserver(weatherObserver);

        assertEquals(insertCount, BULK_INSERT_RECORDS_TO_INSERT);

        // A cursor is your primary interface to the query results.
        Cursor cursor = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                null, // leaving "columns" null just returns all the columns.
                null, // cols for "where" clause
                null, // values for "where" clause
                null  // sort order
        );

        // we should have as many records in the database as we've inserted
        assertEquals(cursor.getCount(), BULK_INSERT_RECORDS_TO_INSERT);

        // and let's make sure they match the ones we created
        cursor.moveToFirst();
        for ( int i = 0; i < BULK_INSERT_RECORDS_TO_INSERT; i++, cursor.moveToNext() ) {
            DataTestUtilities.validateCurrentRecord("testBulkInsert.  Error validating WeatherEntry " + i,
                    cursor, bulkInsertContentValues[i]);
        }
        cursor.close();
    }

    // helper methods

    static ContentValues[] createBulkInsertMovieValues() {
        long millisecondsInADay = 1000*60*60*24;
        ContentValues[] movieList = new ContentValues[BULK_INSERT_RECORDS_TO_INSERT];

        for ( int i = 0; i < BULK_INSERT_RECORDS_TO_INSERT; i++ ) {
            ContentValues movie = new ContentValues();
            movie.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, "backdrop path " + i);
            movie.put(MovieContract.MovieEntry.COLUMN_MOVIEDB_ID, i);
            movie.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_LAN, "en");
            movie.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE, "Test " + i);
            movie.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, "Test Overview " + i);
            movie.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, DataTestUtilities.TEST_DATE);
            movie.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, "path " + i);
            movie.put(MovieContract.MovieEntry.COLUMN_POPULARITY, 50);
            movie.put(MovieContract.MovieEntry.COLUMN_TITLE, "Test Title " + i);
            movie.put(MovieContract.MovieEntry.COLUMN_VIDEO, false);
            movie.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE, 50);
            movie.put(MovieContract.MovieEntry.COLUMN_VOTE_COUNT, 50);
            movieList[i] = movie;
        }
        return movieList;
    }

    public void deleteAllRecords() {
        deleteAllRecordsFromProvider();
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
