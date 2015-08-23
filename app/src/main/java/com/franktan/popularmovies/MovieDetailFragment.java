package com.franktan.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.franktan.popularmovies.data.MovieContract;
import com.franktan.popularmovies.util.Constants;
import com.franktan.popularmovies.util.Parser;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DETAIL_LOADER = 0;
    private int mMovieId = -1;

    ImageView mMovieTrailer;
    ImageView mMoviePoster;
    TextView mMovieTitle;
    TextView mMovieReleaseDate;
    TextView mOriginalLanguage;
    TextView mRatingText;
    TextView mVoteCount;
    TextView mOverview;

    private static final String[] MOVIE_COLUMNS = {
            MovieContract.MovieEntry.TABLE_NAME + "." + MovieContract.MovieEntry._ID,
            MovieContract.MovieEntry.COLUMN_BACKDROP_PATH,
            MovieContract.MovieEntry.COLUMN_ORIGINAL_LAN,
            MovieContract.MovieEntry.COLUMN_OVERVIEW,
            MovieContract.MovieEntry.COLUMN_RELEASE_DATE,
            MovieContract.MovieEntry.COLUMN_POSTER_PATH,
            MovieContract.MovieEntry.COLUMN_TITLE,
            MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE,
            MovieContract.MovieEntry.COLUMN_VOTE_COUNT
    };

    // These indices are tied to FORECAST_COLUMNS.  If FORECAST_COLUMNS changes, these
    // must change.
    static final int COL_MOVIE_ID               = 0;
    static final int COL_MOVIE_BACKDROP_PATH    = 1;
    static final int COL_MOVIE_ORIGINAL_LAN     = 2;
    static final int COL_MOVIE_OVERVIEW         = 3;
    static final int COL_MOVIE_RELEASE_DATE     = 4;
    static final int COL_MOVIE_POSTER_PATH      = 5;
    static final int COL_MOVIE_TITLE            = 6;
    static final int COL_MOVIE_VOTE_AVERAGE     = 7;
    static final int COL_MOVIE_VOTE_COUNT       = 8;

    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        mMovieTrailer       = (ImageView)   view.findViewById(R.id.movie_trailer);
        mMoviePoster        = (ImageView)   view.findViewById(R.id.movie_poster);
        mMovieTitle         = (TextView)    view.findViewById(R.id.movie_title);
        mMovieReleaseDate   = (TextView)    view.findViewById(R.id.release_date);
        mOriginalLanguage   = (TextView)    view.findViewById(R.id.original_language);
        mRatingText         = (TextView)    view.findViewById(R.id.rating_text);
        mVoteCount          = (TextView)    view.findViewById(R.id.vote_count);
        mOverview           = (TextView)    view.findViewById(R.id.overview);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Intent intent = activity.getIntent();
        mMovieId = intent.getIntExtra("Id",-1);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if(mMovieId != -1) {
            getLoaderManager().initLoader(DETAIL_LOADER, null, this);
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getActivity(),
                MovieContract.MovieEntry.buildMovieUri(mMovieId),
                MOVIE_COLUMNS,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //nothing needed
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && !cursor.moveToFirst()){
            return;
        }
        String backdropPath = Constants.BACKDROP_BASE_PATH + cursor.getString(COL_MOVIE_BACKDROP_PATH);
        String posterPath   = Constants.POSTER_BASE_PATH + cursor.getString(COL_MOVIE_POSTER_PATH);
        String title        = cursor.getString(COL_MOVIE_TITLE);
        long releaseDate     = cursor.getLong(COL_MOVIE_RELEASE_DATE);
        String language     = cursor.getString(COL_MOVIE_ORIGINAL_LAN);
        Double voteAverage  = cursor.getDouble(COL_MOVIE_VOTE_AVERAGE);
        int voteCount       = cursor.getInt(COL_MOVIE_VOTE_COUNT);
        String overview     = cursor.getString(COL_MOVIE_OVERVIEW);
        
        Picasso.with(getActivity())
                .load(backdropPath)
                .placeholder(R.drawable.backdrop_loading_placeholder)
                .error(R.drawable.backdrop_failed_placeholder)
                .fit()
                .centerCrop()
                .into(mMovieTrailer);
        Picasso.with(getActivity())
                .load(posterPath)
                .placeholder(R.drawable.poster_loading_placeholder)
                .error(R.drawable.poster_failed_placeholder)
                .fit()
                .centerCrop()
                .into(mMoviePoster);
        mMovieTitle.setText(title);
        mMovieReleaseDate.setText(Parser.humanDateStringFromMiliseconds(releaseDate));
        mOriginalLanguage.setText(language);
//        mRatingBar;
        mRatingText.setText(String.valueOf(voteAverage) + "/10");
        mVoteCount.setText(String.valueOf(voteCount) + " votes");
        mOverview.setText(overview);
    }

    public void showDetailsbyMovieId (int movieId) {
        mMovieId = movieId;
        getLoaderManager().restartLoader(DETAIL_LOADER, null, this);
    }
}
