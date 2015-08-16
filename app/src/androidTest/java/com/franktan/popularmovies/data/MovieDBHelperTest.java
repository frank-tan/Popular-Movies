package com.franktan.popularmovies.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import java.util.HashSet;

/**
 * Created by tan on 15/08/2015.
 */
public class MovieDBHelperTest extends AndroidTestCase {

    public void setUp() {
        deleteTheDatabase();
    }

    public void testCreateDb () throws Throwable {
        // build a HashSet of all of the table names we wish to look for
        // Note that there will be another table in the DB that stores the
        // Android metadata (db version information)
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(MovieContract.MovieEntry.TABLE_NAME);

        // Delete existing database
        deleteTheDatabase();
        // Create an open a new database
        SQLiteDatabase db = new MovieDBHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());

        // have we created the tables we want?
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Tables should exists in the newly created database",
                c.moveToFirst());

        // go through each table in the current database and
        // remove corresponding table from our list
        do {
            tableNameHashSet.remove(c.getString(0));
        } while( c.moveToNext() );
        // at the end of this loop, all tables we want should have been removed from the hashset

        assertTrue("Our tables should have been created in the database",
                tableNameHashSet.isEmpty());

        // now, do our tables contain the correct columns?
        c = db.rawQuery("PRAGMA table_info(" + MovieContract.MovieEntry.TABLE_NAME + ")",
                null);

        assertTrue("Our movie table should have columns", c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> movieColumnSet = new HashSet<String>();
        movieColumnSet.add(MovieContract.MovieEntry._ID);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_MOVIEDB_ID);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_ORIGINAL_LAN);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_OVERVIEW);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_RELEASE_DATE);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_POSTER_PATH);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_POPULARITY);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_TITLE);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_VIDEO);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE);
        movieColumnSet.add(MovieContract.MovieEntry.COLUMN_VOTE_COUNT);

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            movieColumnSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("Movie table should contains all columns we defined",
                movieColumnSet.isEmpty());
        db.close();
    }

    public void testMovieTable() {
        // First step: Get reference to writable database
        SQLiteDatabase db = new MovieDBHelper(mContext).getWritableDatabase();

        assertEquals(true, db.isOpen());
        insertMovie(db);

        // Finally, close the cursor and database

        db.close();
    }
    /**
     * Helper methods
     */
    private void deleteTheDatabase() {
        mContext.deleteDatabase(MovieDBHelper.DATABASE_NAME);
    }
    private Long insertMovie(SQLiteDatabase db){
        ContentValues movieEntry = DateTestUtilities.createMovieEntry();
        Long rowId = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, movieEntry);
        assertTrue("Insertion should return row id",rowId != -1);
        Cursor cursor = db.query(MovieContract.MovieEntry.TABLE_NAME,null,null,null,null,null,null);
        assertTrue("The inserted record should be available", cursor.moveToFirst());
        DateTestUtilities.validateCurrentRecord("Verifying inserted value failed", cursor, movieEntry);
        assertFalse("Should be only one row", cursor.moveToNext());
        cursor.close();
        return rowId;
    }
}
