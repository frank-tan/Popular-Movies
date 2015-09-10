package com.franktan.popularmovies.data;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import com.franktan.popularmovies.data.favorite.FavoriteColumns;
import com.franktan.popularmovies.data.favorite.FavoriteCursor;
import com.franktan.popularmovies.data.favorite.FavoriteSelection;
import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.movie.MovieCursor;
import com.franktan.popularmovies.data.movie.MovieSelection;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreCursor;
import com.franktan.popularmovies.data.moviegenre.MovieGenreSelection;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.trailer.TrailerColumns;
import com.franktan.popularmovies.util.Constants;

/**
 * Created by tan on 15/08/2015.
 */
public class MovieProviderTest extends AndroidTestCase {

    static private final int BULK_INSERT_RECORDS_TO_INSERT = 10;
    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        deleteAllRecords();
    }

    /**
     * Test content provider is registered properly
     */
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
                            " instead of authority: " + MovieProvider.AUTHORITY,
                    providerInfo.authority, MovieProvider.AUTHORITY);
        } catch (PackageManager.NameNotFoundException e) {
            // I guess the provider isn't registered correctly.
            assertTrue("Error: MovieProvider not registered at " + mContext.getPackageName(),
                    false);
        }
    }

    /**
     * Test getType on movie uri
     */
    public void testProviderGetTypeMovie() {
        // content://com.franktan.popularmovies/movie/
        String type = mContext.getContentResolver().getType(MovieColumns.CONTENT_URI);

        // vnd.android.cursor.dir/movie
        assertTrue("the Movie CONTENT_URI should return correct CONTENT_TYPE",
                type.equals(TYPE_CURSOR_DIR + MovieColumns.TABLE_NAME));

        int id = 1;
        // content://com.franktan.popularmovie/movie/1
        type = mContext.getContentResolver().getType(
                ContentUris.withAppendedId(MovieColumns.CONTENT_URI,id));
        Log.d(Constants.APP_NAME, type);
        // vnd.android.cursor.item/movie/1
        assertTrue("Movie uri content type should be parsed",
                type.equals(TYPE_CURSOR_ITEM+MovieColumns.TABLE_NAME));
    }

    /**
     * Test getType on trailer uri
     */
    public void testProviderGetTypeTrailer() {
        String type = mContext.getContentResolver().getType(TrailerColumns.CONTENT_URI);

        assertTrue("the Trailer CONTENT_URI should return correct CONTENT_TYPE",
                type.equals(TYPE_CURSOR_DIR + TrailerColumns.TABLE_NAME));

        int id = 1;
        type = mContext.getContentResolver().getType(
                ContentUris.withAppendedId(TrailerColumns.CONTENT_URI,id));
        Log.d(Constants.APP_NAME,type);
        assertTrue("Trailer uri content type should be parsed",
                type.equals(TYPE_CURSOR_ITEM+TrailerColumns.TABLE_NAME));
    }

    /**
     * Test getType on review uri
     */
    public void testProviderGetTypeReview() {
        String type = mContext.getContentResolver().getType(ReviewColumns.CONTENT_URI);

        assertTrue("the Review CONTENT_URI should return correct CONTENT_TYPE",
                type.equals(TYPE_CURSOR_DIR + ReviewColumns.TABLE_NAME));

        int id = 1;
        type = mContext.getContentResolver().getType(
                ContentUris.withAppendedId(ReviewColumns.CONTENT_URI,id));
        Log.d(Constants.APP_NAME,type);
        assertTrue("Review uri content type should be parsed",
                type.equals(TYPE_CURSOR_ITEM+ReviewColumns.TABLE_NAME));
    }

    /**
     * Test getType on Genre uri
     */
    public void testProviderGetTypeGenre() {
        String type = mContext.getContentResolver().getType(GenreColumns.CONTENT_URI);

        assertTrue("the Genre CONTENT_URI should return correct CONTENT_TYPE",
                type.equals(TYPE_CURSOR_DIR + GenreColumns.TABLE_NAME));

        int id = 1;
        type = mContext.getContentResolver().getType(
                ContentUris.withAppendedId(GenreColumns.CONTENT_URI,id));
        Log.d(Constants.APP_NAME, type);
        assertTrue("Genre uri content type should be parsed",
                type.equals(TYPE_CURSOR_ITEM + GenreColumns.TABLE_NAME));
    }

    /**
     * Test getType on movie_genre uri
     */
    public void testProviderGetTypeMovieGenre() {
        String type = mContext.getContentResolver().getType(MovieGenreColumns.CONTENT_URI);

        assertTrue("the MovieGenre CONTENT_URI should return correct CONTENT_TYPE",
                type.equals(TYPE_CURSOR_DIR + MovieGenreColumns.TABLE_NAME));

        int id = 1;
        type = mContext.getContentResolver().getType(
                ContentUris.withAppendedId(MovieGenreColumns.CONTENT_URI,id));
        Log.d(Constants.APP_NAME,type);
        assertTrue("MovieGenre uri content type should be parsed",
                type.equals(TYPE_CURSOR_ITEM+MovieGenreColumns.TABLE_NAME));
    }

    /**
     * Test getType on favorite uri
     */
    public void testProviderGetTypeFavorite() {
        String type = mContext.getContentResolver().getType(FavoriteColumns.CONTENT_URI);

        assertTrue("the favorite CONTENT_URI should return correct CONTENT_TYPE",
                type.equals(TYPE_CURSOR_DIR + FavoriteColumns.TABLE_NAME));

        int id = 1;
        type = mContext.getContentResolver().getType(
                ContentUris.withAppendedId(FavoriteColumns.CONTENT_URI,id));
        Log.d(Constants.APP_NAME, type);
        assertTrue("favorite uri content type should be parsed",
                type.equals(TYPE_CURSOR_ITEM + FavoriteColumns.TABLE_NAME));
    }

    /**
     * Insert using direct SQLiteDbHelper and test query using content provider
     */
    public void testProviderQuery() {

        long movieRowId = DataTestUtilities.insertMovieTestEntry(mContext);

        // Test the basic content provider query
        Cursor movieCursor = mContext.getContentResolver().query(
                MovieColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        // Make sure we get the correct cursor out of the database
        DataTestUtilities.validateMovieCursor("basic movie query", movieCursor, DataTestUtilities.createMovieEntry());

        movieCursor.close();

        MovieSelection where = new MovieSelection();
        where.id(movieRowId);
        Cursor cursorById = mContext.getContentResolver().query(MovieColumns.CONTENT_URI,null,where.sel(),where.args(),null);
        DataTestUtilities.validateMovieCursor("query by id using library's syntax", cursorById, DataTestUtilities.createMovieEntry());

        MovieCursor mc = new MovieCursor(cursorById);
        mc.moveToFirst();
        assertEquals("generated MovieCursor should work", movieRowId, mc.getId());
        assertEquals("generated MovieCursor should work", 76341, mc.getMovieMoviedbId());
        assertEquals("generated MovieCursor should work", 54.32, mc.getPopularity());
        assertTrue("generated MovieCursor should work", mc.getPosterPath().equals("/kqjL17yufvn9OVLyXYpvtyrFfak.jpg"));
        mc.close();
        cursorById.close();
    }

    /**
     * Test insert using content provider
     */
    public void testProviderInsert() {
        ContentValues movieTestValues = DataTestUtilities.createMovieEntry();
        ContentValues genreTestValues = DataTestUtilities.createGenreEntry();

        // Register a content observer for our insert.  This time, directly with the content resolver
        DataTestUtilities.TestContentObserver tco = DataTestUtilities.getTestContentObserver();

        ContentResolver contentResolver = mContext.getContentResolver();

        /*
            insert movie record
         */
        contentResolver.registerContentObserver(MovieColumns.CONTENT_URI, true, tco);
        Uri movieUri = contentResolver.insert(MovieColumns.CONTENT_URI, movieTestValues);
        tco.waitForNotificationOrFail();
        contentResolver.unregisterContentObserver(tco);

        /*
            insert genre record
          */
        DataTestUtilities.TestContentObserver tco2 = DataTestUtilities.getTestContentObserver();
        contentResolver.registerContentObserver(GenreColumns.CONTENT_URI, true, tco2);
        Uri genreUri = contentResolver.insert(GenreColumns.CONTENT_URI, genreTestValues);
        tco2.waitForNotificationOrFail();
        contentResolver.unregisterContentObserver(tco2);

        long movieRowId = ContentUris.parseId(movieUri);
        long genreRowId = ContentUris.parseId(genreUri);

        ContentValues movieGenreTestValues = DataTestUtilities.createMovieGenreEntry(movieRowId, genreRowId);

        /*
            insert movie_genre intersection record
          */
        DataTestUtilities.TestContentObserver tco3 = DataTestUtilities.getTestContentObserver();
        contentResolver.registerContentObserver(MovieGenreColumns.CONTENT_URI, true, tco3);
        Uri movieGenreUri = contentResolver.insert(MovieGenreColumns.CONTENT_URI, movieGenreTestValues);
        tco3.waitForNotificationOrFail();
        contentResolver.unregisterContentObserver(tco3);

        long movieGenreRowId = ContentUris.parseId(movieGenreUri);

        /*
            Verify we got a movie, moviegenre, genre join row back.
          */
        assertTrue("Valid row id should be returned from content provider movie insert", movieRowId != -1);
        assertTrue("Valid row id should be returned from content provider genre insert", genreRowId != -1);
        assertTrue("Valid row id should be returned from content provider movie_genre insert", movieGenreRowId != -1);

        String[] projection = {
                MovieColumns._ID,
                MovieColumns.TITLE,
                MovieColumns.BACKDROP_PATH,
                MovieGenreColumns._ID,
                MovieGenreColumns.MOVIE_ID,
                MovieGenreColumns.GENRE_ID,
                GenreColumns._ID,
                GenreColumns.GENRE_MOVIEDB_ID,
                GenreColumns.NAME
        };

        MovieGenreSelection movieGenreSelection = new MovieGenreSelection();
        MovieGenreCursor cursor = movieGenreSelection.query(mContext.getContentResolver(),projection);

        assertTrue("Should return one record", cursor.moveToNext());

        assertTrue("movie record title should be correct", cursor.getMovieTitle().equals("Mad Max: Fury Road"));
        assertTrue("movie record backdroppath should be correct", cursor.getMovieBackdropPath().equals("/tbhdm8UJAb4ViCTsulYFL3lxMCd.jpg"));
        assertEquals("intersection record movie id should be movie record's row id", movieRowId, cursor.getMovieId());
        assertEquals("intersection record genre id should be genre record's row id", genreRowId, cursor.getGenreId());
        assertTrue("genre name should be correct", cursor.getGenreName().equals("Action"));
        assertEquals("genre moviedb id should be correct", 28, cursor.getGenreGenreMoviedbId());

        cursor.close();

        /*
            insert a record to favorite table
         */
        ContentValues favoriteTestValues = DataTestUtilities.createFavoriteEntry(76341, DataTestUtilities.TEST_DATE);

        DataTestUtilities.TestContentObserver tco4 = DataTestUtilities.getTestContentObserver();
        contentResolver.registerContentObserver(FavoriteColumns.CONTENT_URI, true, tco4);
        Uri favoriteUri = contentResolver.insert(FavoriteColumns.CONTENT_URI, favoriteTestValues);
        tco4.waitForNotificationOrFail();
        contentResolver.unregisterContentObserver(tco4);

        long favoriteRowId = ContentUris.parseId(favoriteUri);

        String[] favoriteMovieProjection = {
                FavoriteColumns._ID,
                FavoriteColumns.CREATED,
                MovieColumns.TITLE,
                MovieColumns.MOVIE_MOVIEDB_ID
        };

        FavoriteSelection favoriteSelection = new FavoriteSelection();
        FavoriteCursor favoriteCursor = favoriteSelection.query(mContext.getContentResolver(),favoriteMovieProjection);

        assertTrue("Should return record", favoriteCursor.moveToNext());
        assertEquals("should return one record", 1, favoriteCursor.getCount());
        assertEquals("movie id should be correct", 76341, favoriteCursor.getMovieMovieMoviedbId());
        assertTrue("movie title should be correct", favoriteCursor.getMovieTitle().equals("Mad Max: Fury Road"));
        assertEquals("favorite id should be correct", favoriteRowId, favoriteCursor.getId());
        assertEquals("favorite date time should be correct", String.valueOf(DataTestUtilities.TEST_DATE), String.valueOf(favoriteCursor.getCreated()));

        favoriteCursor.close();
    }

    public void testProviderUpdate() {
        // Create a new map of values, where column names are the keys
        ContentValues values = DataTestUtilities.createMovieEntry();

        Uri movieUri = mContext.getContentResolver().
                insert(MovieColumns.CONTENT_URI, values);
        long movieRowId = ContentUris.parseId(movieUri);

        // Verify we got a row back.
        assertTrue("Should be able to insert a record and get back a valid row id", movieRowId != -1);

        ContentValues updatedValues = new ContentValues(values);
        updatedValues.put(MovieColumns._ID, movieRowId);
        updatedValues.put(MovieColumns.ORIGINAL_LAN, "Aussie English");

        // Create a cursor with observer to make sure that the content provider is notifying
        // the observers as expected
        Cursor movieCursor = mContext.getContentResolver().query(MovieColumns.CONTENT_URI, null, null, null, null);

        DataTestUtilities.TestContentObserver tco = DataTestUtilities.getTestContentObserver();
        movieCursor.registerContentObserver(tco);

        int count = mContext.getContentResolver().update(
                MovieColumns.CONTENT_URI, updatedValues, MovieColumns._ID + "= ?",
                new String[] { Long.toString(movieRowId)});
        assertEquals("Number of records updated should be 1", 1, count);

        // Test to make sure our observer is called.  If not, we throw an assertion.
        tco.waitForNotificationOrFail();

        movieCursor.unregisterContentObserver(tco);
        movieCursor.close();

        MovieSelection where = new MovieSelection();
        where.id(movieRowId);

        // A cursor is your primary interface to the query results.
        Cursor cursor = mContext.getContentResolver().query(
                MovieColumns.CONTENT_URI,
                null,   // projection
                where.sel(),
                where.args(),
                null    // sort order
        );

        DataTestUtilities.validateMovieCursor("The record in the database should have been updated",
                cursor, updatedValues);

        cursor.close();
    }

    public void testProviderDelete() {
        testProviderInsert();

        DataTestUtilities.TestContentObserver locationObserver = DataTestUtilities.getTestContentObserver();
        mContext.getContentResolver().registerContentObserver(MovieColumns.CONTENT_URI, true, locationObserver);

        deleteAllRecordsFromProvider();

        locationObserver.waitForNotificationOrFail();

        mContext.getContentResolver().unregisterContentObserver(locationObserver);
    }

    public void testProviderBulkInsert() {

        ContentValues[] bulkInsertContentValues = createBulkInsertMovieValues(0);

        // Register a content observer for our bulk insert.
        DataTestUtilities.TestContentObserver weatherObserver = DataTestUtilities.getTestContentObserver();
        mContext.getContentResolver().registerContentObserver(MovieColumns.CONTENT_URI, true, weatherObserver);

        int insertCount = mContext.getContentResolver().bulkInsert(MovieColumns.CONTENT_URI, bulkInsertContentValues);

        weatherObserver.waitForNotificationOrFail();
        mContext.getContentResolver().unregisterContentObserver(weatherObserver);

        assertEquals(insertCount, BULK_INSERT_RECORDS_TO_INSERT);

        // A cursor is your primary interface to the query results.
        Cursor cursor = mContext.getContentResolver().query(
                MovieColumns.CONTENT_URI,
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
            DataTestUtilities.validateMovieRecordUnderCursor("testBulkInsert.  Error validating WeatherEntry " + i,
                    cursor, bulkInsertContentValues[i]);
        }
        cursor.close();
    }

    public void testProviderBulkUpsert() {

        testProviderBulkInsert();

        // A cursor is your primary interface to the query results.
        Cursor cursor = mContext.getContentResolver().query(
                MovieColumns.CONTENT_URI,
                null, // leaving "columns" null just returns all the columns.
                null, // cols for "where" clause
                null, // values for "where" clause
                null  // sort order
        );

        // we should have as many records in the database as we've inserted
        assertEquals("Should already have " + BULK_INSERT_RECORDS_TO_INSERT + "records", BULK_INSERT_RECORDS_TO_INSERT, cursor.getCount());

        ContentValues[] bulkUpsertContentValues = createBulkInsertMovieValues(1);

        // Register a content observer for our bulk insert.
        DataTestUtilities.TestContentObserver weatherObserver = DataTestUtilities.getTestContentObserver();
        mContext.getContentResolver().registerContentObserver(MovieColumns.CONTENT_URI, true, weatherObserver);

        int insertCount = mContext.getContentResolver().bulkInsert(MovieColumns.CONTENT_URI, bulkUpsertContentValues);

        weatherObserver.waitForNotificationOrFail();
        mContext.getContentResolver().unregisterContentObserver(weatherObserver);

        assertEquals("Should insert " + BULK_INSERT_RECORDS_TO_INSERT + "records", BULK_INSERT_RECORDS_TO_INSERT, insertCount);

        // A cursor is your primary interface to the query results.
        cursor = mContext.getContentResolver().query(
                MovieColumns.CONTENT_URI,
                null, // leaving "columns" null just returns all the columns.
                null, // cols for "where" clause
                null, // values for "where" clause
                null  // sort order
        );

        // we should have as many records in the database as we've inserted
        assertEquals("Should still have " + BULK_INSERT_RECORDS_TO_INSERT + " records", BULK_INSERT_RECORDS_TO_INSERT, cursor.getCount());

        // and let's make sure they match the ones we created
        cursor.moveToFirst();
        for ( int i = 0; i < BULK_INSERT_RECORDS_TO_INSERT; i++, cursor.moveToNext() ) {
            DataTestUtilities.validateMovieRecordUnderCursor("testBulkInsert.  Error validating WeatherEntry " + i,
                    cursor, bulkUpsertContentValues[i]);
        }
        cursor.close();
    }

    /*
     * helper methods
     */

    static ContentValues[] createBulkInsertMovieValues(int offset) {
        ContentValues[] movieList = new ContentValues[BULK_INSERT_RECORDS_TO_INSERT];

        for ( int i = 0; i < BULK_INSERT_RECORDS_TO_INSERT; i++ ) {
            ContentValues movie = new ContentValues();
            movie.put(MovieColumns.BACKDROP_PATH, "backdrop path " + i + offset);
            movie.put(MovieColumns.MOVIE_MOVIEDB_ID, i);
            movie.put(MovieColumns.ORIGINAL_LAN, "en");
            movie.put(MovieColumns.ORIGINAL_TITLE, "Test " + i + offset);
            movie.put(MovieColumns.OVERVIEW, "Test Overview " + i + offset);
            movie.put(MovieColumns.RELEASE_DATE, DataTestUtilities.TEST_DATE);
            movie.put(MovieColumns.POSTER_PATH, "path " + i + offset);
            movie.put(MovieColumns.POPULARITY, 50);
            movie.put(MovieColumns.TITLE, "Test Title " + i + offset);
            movie.put(MovieColumns.VOTE_AVERAGE, 50);
            movie.put(MovieColumns.VOTE_COUNT, 50);
            movieList[i] = movie;
        }
        return movieList;
    }

    public void deleteAllRecords() {
        deleteAllRecordsFromProvider();
    }

    public void deleteAllRecordsFromProvider() {
        deleteAllFavoriteRecords();
        deleteAllMovieRecords();
        deleteAllTrailerRecords();
        deleteAllReviewRecords();
        deleteAllGenreRecords();
        deleteAllMovieGenreRecords();
    }

    public void deleteAllMovieRecords() {
        mContext.getContentResolver().delete(
                MovieColumns.CONTENT_URI,
                null,
                null
        );
        Cursor cursor = mContext.getApplicationContext().getContentResolver().query(
                MovieColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        assertEquals("Should be 0 record in movie table after delete", 0, cursor.getCount());
        cursor.close();
    }

    public void deleteAllTrailerRecords() {
        mContext.getContentResolver().delete(
                TrailerColumns.CONTENT_URI,
                null,
                null
        );
        Cursor cursor = mContext.getContentResolver().query(
                TrailerColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        assertEquals("Should be 0 record in trailer table after delete", 0, cursor.getCount());
        cursor.close();
    }

    public void deleteAllFavoriteRecords() {
        mContext.getContentResolver().delete(
                FavoriteColumns.CONTENT_URI,
                null,
                null
        );
        Cursor cursor = mContext.getContentResolver().query(
                FavoriteColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        assertEquals("Should be 0 record in favorite table after delete", 0, cursor.getCount());
        cursor.close();
    }

    public void deleteAllReviewRecords() {
        mContext.getContentResolver().delete(
                ReviewColumns.CONTENT_URI,
                null,
                null
        );

        Cursor cursor = mContext.getContentResolver().query(
                ReviewColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        assertEquals("Should be 0 record in review table after delete", 0, cursor.getCount());
        cursor.close();
    }

    public void deleteAllGenreRecords() {
        mContext.getContentResolver().delete(
                GenreColumns.CONTENT_URI,
                null,
                null
        );

        Cursor cursor = mContext.getContentResolver().query(
                GenreColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        assertEquals("Should be 0 record in genre table after delete", 0, cursor.getCount());
        cursor.close();
    }

    public void deleteAllMovieGenreRecords() {
        mContext.getContentResolver().delete(
                MovieGenreColumns.CONTENT_URI,
                null,
                null
        );

        Cursor cursor = mContext.getContentResolver().query(
                MovieGenreColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        assertEquals("Should be 0 record in movie_genre table after delete", 0, cursor.getCount());
        cursor.close();
    }
}
