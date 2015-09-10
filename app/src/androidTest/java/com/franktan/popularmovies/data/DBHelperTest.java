package com.franktan.popularmovies.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.franktan.popularmovies.data.favorite.FavoriteColumns;
import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.moviegenre.MovieGenreColumns;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.trailer.TrailerColumns;

import java.util.HashSet;

/**
 * Created by tan on 15/08/2015.
 */
public class DBHelperTest extends AndroidTestCase {

    public void setUp() {
        deletePopularMoviesDatabase();
    }

    public void testCreateDbAndTables () throws Throwable {
        // build a HashSet of all of the table names we wish to look for
        // Note that there will be another table in the DB that stores the
        // Android metadata (db version information)
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(MovieColumns.TABLE_NAME);
        tableNameHashSet.add(GenreColumns.TABLE_NAME);
        tableNameHashSet.add(TrailerColumns.TABLE_NAME);
        tableNameHashSet.add(ReviewColumns.TABLE_NAME);

        // Delete existing database
        deletePopularMoviesDatabase();
        // Create an open a new database
        SQLiteDatabase db = MovieSQLiteOpenHelper.getInstance(
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

        c.close();

        movieTableHasRightColumns(db);
        genreTableHasRightColumns(db);
        movieGenreTableHasRightColumns(db);
        reviewTableHasRightColumns(db);
        trailerTableHasRightColumns(db);
        favoriteTableHasRightColumns(db);

        db.close();
    }

    public void testMovieTableInsertAndUpdate() {
        // First step: Get reference to writable database
        SQLiteDatabase db = MovieSQLiteOpenHelper.getInstance(mContext).getWritableDatabase();

        assertEquals(true, db.isOpen());
        insertMovie(db);

        // Finally, close the cursor and database

        db.close();
    }

    /*
     * Helper methods
     */

    /**
     * Test trailer table has the right colomns
     * @param db
     */
    public void trailerTableHasRightColumns (SQLiteDatabase db) {
        // test Genre table has correct columns
        Cursor c = db.rawQuery("PRAGMA table_info(" + TrailerColumns.TABLE_NAME + ")",
                null);

        assertTrue("Our trailer table should have columns", c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> trailerColumnSet = new HashSet<String>();
        trailerColumnSet.add(TrailerColumns._ID);
        trailerColumnSet.add(TrailerColumns.NAME);
        trailerColumnSet.add(TrailerColumns.SIZE);
        trailerColumnSet.add(TrailerColumns.SOURCE);
        trailerColumnSet.add(TrailerColumns.TYPE);
        trailerColumnSet.add(TrailerColumns.MOVIE_ID);

        int columnNameIndexTrailer = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndexTrailer);
            trailerColumnSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("tailer table should contains all columns we defined",
                trailerColumnSet.isEmpty());
        c.close();
    }

    /**
     * Test favorite table has the right colomns
     * @param db
     */
    public void favoriteTableHasRightColumns (SQLiteDatabase db) {
        // test Genre table has correct columns
        Cursor c = db.rawQuery("PRAGMA table_info(" + FavoriteColumns.TABLE_NAME + ")",
                null);

        assertTrue("Our favorite table should have columns", c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> favoriteColumnSet = new HashSet<>();
        favoriteColumnSet.add(FavoriteColumns._ID);
        favoriteColumnSet.add(FavoriteColumns.FAVORITE_MOVIEDB_ID);
        favoriteColumnSet.add(FavoriteColumns.CREATED);

        int columnNameIndexFavorite = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndexFavorite);
            favoriteColumnSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("favorite table should contains all columns we defined",
                favoriteColumnSet.isEmpty());
        c.close();
    }

    /**
     * Test review table has the right colomns
     * @param db
     */
    public void reviewTableHasRightColumns (SQLiteDatabase db) {
        // test Genre table has correct columns
        Cursor c = db.rawQuery("PRAGMA table_info(" + ReviewColumns.TABLE_NAME + ")",
                null);

        assertTrue("Our review table should have columns", c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> reviewColumnSet = new HashSet<String>();
        reviewColumnSet.add(ReviewColumns._ID);
        reviewColumnSet.add(ReviewColumns.MOVIE_ID);
        reviewColumnSet.add(ReviewColumns.REVIEW_MOVIEDB_ID);
        reviewColumnSet.add(ReviewColumns.AUTHOR);
        reviewColumnSet.add(ReviewColumns.CONTENT);
        reviewColumnSet.add(ReviewColumns.URL);

        int columnNameIndexReview = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndexReview);
            reviewColumnSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("review table should contains all columns we defined",
                reviewColumnSet.isEmpty());
        c.close();
    }

    /**
     * Test movie_genre table has the right colomns
     * @param db
     */
    public void movieGenreTableHasRightColumns (SQLiteDatabase db) {
        // test Genre table has correct columns
        Cursor c = db.rawQuery("PRAGMA table_info(" + MovieGenreColumns.TABLE_NAME + ")",
                null);

        assertTrue("Our movie_genre table should have columns", c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> movieGenreColumnSet = new HashSet<String>();
        movieGenreColumnSet.add(MovieGenreColumns._ID);
        movieGenreColumnSet.add(MovieGenreColumns.MOVIE_ID);
        movieGenreColumnSet.add(MovieGenreColumns.GENRE_ID);

        int columnNameIndexMovieGenre = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndexMovieGenre);
            movieGenreColumnSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("movie_genre table should contains all columns we defined",
                movieGenreColumnSet.isEmpty());
        c.close();
    }

    /**
     * Test genre table has the right colomns
     * @param db
     */
    public void genreTableHasRightColumns (SQLiteDatabase db) {
        // test Genre table has correct columns
        Cursor c = db.rawQuery("PRAGMA table_info(" + GenreColumns.TABLE_NAME + ")",
                null);

        assertTrue("Our genre table should have columns", c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> genreColumnSet = new HashSet<String>();
        genreColumnSet.add(GenreColumns._ID);
        genreColumnSet.add(GenreColumns.GENRE_MOVIEDB_ID);
        genreColumnSet.add(GenreColumns.NAME);

        int columnNameIndexGenre = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndexGenre);
            genreColumnSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("Genre table should contains all columns we defined",
                genreColumnSet.isEmpty());
        c.close();
    }

    /**
     * Test the movie table has the right columns
     * @param db
     */
    public void movieTableHasRightColumns (SQLiteDatabase db) {
        // test movie table has correct columns
        Cursor c = db.rawQuery("PRAGMA table_info(" + MovieColumns.TABLE_NAME + ")",
                null);

        assertTrue("Our movie table should have columns", c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> movieColumnSet = new HashSet<String>();
        movieColumnSet.add(MovieColumns._ID);
        movieColumnSet.add(MovieColumns.BACKDROP_PATH);
        movieColumnSet.add(MovieColumns.MOVIE_MOVIEDB_ID);
        movieColumnSet.add(MovieColumns.ORIGINAL_LAN);
        movieColumnSet.add(MovieColumns.ORIGINAL_TITLE);
        movieColumnSet.add(MovieColumns.OVERVIEW);
        movieColumnSet.add(MovieColumns.RELEASE_DATE);
        movieColumnSet.add(MovieColumns.POSTER_PATH);
        movieColumnSet.add(MovieColumns.POPULARITY);
        movieColumnSet.add(MovieColumns.TITLE);
        movieColumnSet.add(MovieColumns.VOTE_AVERAGE);
        movieColumnSet.add(MovieColumns.VOTE_COUNT);

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            movieColumnSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("Movie table should contains all columns we defined",
                movieColumnSet.isEmpty());
        c.close();
    }


    private void deletePopularMoviesDatabase() {
        mContext.deleteDatabase(MovieSQLiteOpenHelper.DATABASE_FILE_NAME);
    }

    private Long insertMovie(SQLiteDatabase db){
        ContentValues movieEntry = DataTestUtilities.createMovieEntry();
        Long rowId = db.insert(MovieColumns.TABLE_NAME, null, movieEntry);
        assertTrue("Insertion should return row id",rowId != -1);
        Cursor cursor = db.query(MovieColumns.TABLE_NAME,null,null,null,null,null,null);
        assertTrue("The inserted record should be available", cursor.moveToFirst());
        DataTestUtilities.validateMovieRecordUnderCursor("Verifying inserted value failed", cursor, movieEntry);
        assertFalse("Should be only one row", cursor.moveToNext());
        cursor.close();
        return rowId;
    }
}
