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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private MovieGridAdapter mMovieGridAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesGridFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesGridFragment newInstance(String param1, String param2) {
        MoviesGridFragment fragment = new MoviesGridFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MoviesGridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(Constants.APP_NAME, "MoviesGridFragment onCreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        String newSortOrderPref = Utilities.getSortOrderPreference(getActivity());
        if(!newSortOrderPref.equals(mSortOrderPreference)) {
            getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
            mSortOrderPreference = newSortOrderPref;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(Constants.APP_NAME, "onActivityCreated: to initLoader");
        getLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_grid, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.moviesgridview);
        mMovieGridAdapter = new MovieGridAdapter(getActivity(), null, 0);

        gridview.setAdapter(mMovieGridAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                // CursorAdapter returns a cursor at the correct position for getItem(), or null
                // if it cannot seek to that position.
                if (cursor == null) {
                    Log.i(Constants.APP_NAME,"item clicked, but cursor is null");
                    return;
                }
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("Id", cursor.getInt(COL_MOVIE_ID));
                startActivity(intent);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.i(Constants.APP_NAME, "onCreateLoader");
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
        Log.i(Constants.APP_NAME, "onLoadFinished count: "+cursor.getCount());
        mMovieGridAdapter.swapCursor(cursor);
        //TODO: to keep scrolling position, add code here
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        Log.i(Constants.APP_NAME, "onLoaderReset");
        mMovieGridAdapter.swapCursor(null);
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
