package com.franktan.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.franktan.popularmovies.data.MovieContract;
import com.franktan.popularmovies.util.Constants;
import com.franktan.popularmovies.util.Utilities;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoviesGridFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoviesGridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] MOVIE_COLUMNS = {
            MovieContract.MovieEntry.TABLE_NAME + "." + MovieContract.MovieEntry._ID,
            MovieContract.MovieEntry.COLUMN_POSTER_PATH
    };
    static final int COL_MOVIE_ID = 0;
    static final int COL_POSTER_PATH = 1;
    private static final int MOVIE_LOADER_ID = 0;
    private static String mSortOrderPreference;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SELECTION = "selection";

    private int mSelection;

    private OnFragmentInteractionListener mListener;
    private MovieGridAdapter mMovieGridAdapter;
    GridView mGridView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param selection the active selection.
     * @return A new instance of fragment MoviesGridFragment.
     */
    public static MoviesGridFragment newInstance(int selection) {
        MoviesGridFragment fragment = new MoviesGridFragment();
        Bundle args = new Bundle();
        args.putInt(SELECTION, selection);
        fragment.setArguments(args);
        return fragment;
    }

    public MoviesGridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null && savedInstanceState.containsKey(SELECTION)) {
            int savedPosition = savedInstanceState.getInt(SELECTION);
            if(savedPosition >= 0) {
                mSelection = savedPosition;
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        String newSortOrderPref = Utilities.getSortOrderPreference(getActivity());
        if(!newSortOrderPref.equals(mSortOrderPreference)) {
            mSortOrderPreference = newSortOrderPref;
            getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
            goToSelectedMovie(0);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_grid, container, false);

        mGridView = (GridView) view.findViewById(R.id.moviesgridview);
        mMovieGridAdapter = new MovieGridAdapter(getActivity(), null, 0);

        mGridView.setAdapter(mMovieGridAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                mSelection = position;
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                // CursorAdapter returns a cursor at the correct position for getItem(), or null
                // if it cannot seek to that position.
                if (cursor == null) {
                    Log.i(Constants.APP_NAME, "item clicked, but cursor is null");
                    return;
                }
                int movieId = cursor.getInt(COL_MOVIE_ID);
                if (mListener.isInTwoPaneMode()) {
                    mListener.onMovieItemSelected(movieId);
                } else {
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                    intent.putExtra("Id", movieId);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // save the current active selection
        if(mSelection != GridView.INVALID_POSITION) {
            outState.putInt(SELECTION,mSelection);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        mSortOrderPreference = Utilities.getSortOrderPreference(getActivity());

        Uri movieUri = MovieContract.MovieEntry.CONTENT_URI;
        String sortOrder;
        if(mSortOrderPreference.equals("Rating")){
            sortOrder = MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE + " DESC";
        } else {
            sortOrder = MovieContract.MovieEntry.COLUMN_POPULARITY + " DESC";
        }
        return new CursorLoader(
                getActivity(),
                movieUri,
                MOVIE_COLUMNS,
                null,
                null,
                sortOrder
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mMovieGridAdapter.swapCursor(cursor);

        //keep scrolling position, add code here
        goToSelectedMovie(mSelection);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mMovieGridAdapter.swapCursor(null);
    }

    private void goToSelectedMovie (int selection) {
        mSelection = selection;
        if(mSelection != GridView.INVALID_POSITION) {
            mGridView.setSelection(mSelection);
            mGridView.setItemChecked(mSelection, true);
            if(mListener.isInTwoPaneMode()) {
                int movieId = (int) mMovieGridAdapter.getItemId(mSelection);
                mListener.onMovieItemSelected(movieId);
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        boolean isInTwoPaneMode();

        void onMovieItemSelected(int movieId);
    }

}
