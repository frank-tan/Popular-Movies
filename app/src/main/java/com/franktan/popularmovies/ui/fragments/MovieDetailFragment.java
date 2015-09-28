package com.franktan.popularmovies.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.franktan.popularmovies.R;
import com.franktan.popularmovies.data.favorite.FavoriteColumns;
import com.franktan.popularmovies.data.favorite.FavoriteContentValues;
import com.franktan.popularmovies.data.favorite.FavoriteCursor;
import com.franktan.popularmovies.data.favorite.FavoriteSelection;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.data.movie.MovieCursor;
import com.franktan.popularmovies.data.review.ReviewColumns;
import com.franktan.popularmovies.data.review.ReviewCursor;
import com.franktan.popularmovies.data.trailer.TrailerColumns;
import com.franktan.popularmovies.data.trailer.TrailerCursor;
import com.franktan.popularmovies.service.MovieDetailsIntentService;
import com.franktan.popularmovies.ui.views.PagerIndicator;
import com.franktan.popularmovies.util.Constants;
import com.franktan.popularmovies.util.Parser;
import com.franktan.popularmovies.util.Utilities;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment
        extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>, PagerIndicator.SetPage {

    private static final int DETAIL_LOADER = 0;
    private long mMovieDBId = -1;

    private TrailerPagerAdapter mTrailerPagerAdapter = null;
    private PagerIndicator mPagerIndicator = null;

    private ImageView mMovieBackdrop;
    private ImageView mMoviePoster;
    private TextView mMovieTitle;
    private TextView mMovieReleaseDate;
    private TextView mOriginalLanguage;
    private TextView mRatingText;
    private TextView mVoteCount;
    private TextView mOverview;
    private LinearLayout mReviewSection;
    private LinearLayout mTrailerSection;
    private ViewPager mTrailerPager;
    private CheckBox mFavoriteCheckbox;
    private Context mContext;

    private ShareActionProvider mShareActionProvider;

    private static final String[] MOVIE_COLUMNS = {
            MovieColumns.TABLE_NAME + "." + MovieColumns._ID,
            MovieColumns.TABLE_NAME + "." + MovieColumns.BACKDROP_PATH,
            MovieColumns.TABLE_NAME + "." + MovieColumns.ORIGINAL_LAN,
            MovieColumns.TABLE_NAME + "." + MovieColumns.OVERVIEW,
            MovieColumns.TABLE_NAME + "." + MovieColumns.RELEASE_DATE,
            MovieColumns.TABLE_NAME + "." + MovieColumns.POSTER_PATH,
            MovieColumns.TABLE_NAME + "." + MovieColumns.TITLE,
            MovieColumns.TABLE_NAME + "." + MovieColumns.VOTE_AVERAGE,
            MovieColumns.TABLE_NAME + "." + MovieColumns.VOTE_COUNT,
            ReviewColumns.TABLE_NAME + "." + ReviewColumns.AUTHOR,
            ReviewColumns.TABLE_NAME + "." + ReviewColumns.CONTENT,
            ReviewColumns.TABLE_NAME + "." + ReviewColumns.URL,
            TrailerColumns.TABLE_NAME + "." + TrailerColumns.TYPE,
            TrailerColumns.TABLE_NAME + "." + TrailerColumns.NAME,
            TrailerColumns.TABLE_NAME + "." + TrailerColumns.SIZE,
            TrailerColumns.TABLE_NAME + "." + TrailerColumns.SOURCE,
            FavoriteColumns.TABLE_NAME + "." + FavoriteColumns._ID,
            FavoriteColumns.TABLE_NAME + "." + FavoriteColumns.CREATED
    };

    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        mMovieBackdrop = (ImageView)   view.findViewById(R.id.movie_backdrop);
        mMoviePoster        = (ImageView)   view.findViewById(R.id.movie_poster);
        mMovieTitle         = (TextView)    view.findViewById(R.id.movie_title);
        mMovieReleaseDate   = (TextView)    view.findViewById(R.id.release_date);
        mOriginalLanguage   = (TextView)    view.findViewById(R.id.original_language);
        mRatingText         = (TextView)    view.findViewById(R.id.rating_text);
        mVoteCount          = (TextView)    view.findViewById(R.id.vote_count);
        mOverview           = (TextView)    view.findViewById(R.id.overview);
        mReviewSection      = (LinearLayout)view.findViewById(R.id.review_section);
        mTrailerSection     = (LinearLayout)view.findViewById(R.id.trailer_section);
        mFavoriteCheckbox = (CheckBox)    view.findViewById(R.id.favorite_checkbox);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mTrailerPagerAdapter != null) {
            mTrailerPagerAdapter.releaseLoaders();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        Activity activity;
        if (context instanceof Activity){
            activity = (Activity) context;
        } else {
            activity = getActivity();
        }

        Intent intent = activity.getIntent();
        mMovieDBId = intent.getLongExtra(Constants.MOVIEDB_ID,-1);
        Log.i(Constants.APP_NAME, "mMovieDBId from intent is " + mMovieDBId);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if(mMovieDBId != -1) {
            // Invoke IntentService to retrieve reviews and trailers
            startMovieDetailsIntentService();

            // Query database
            getLoaderManager().initLoader(DETAIL_LOADER, null, this);
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);

        // Set up ShareActionProvider's default share intent
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);

        super.onCreateOptionsMenu(menu, inflater);
    }

    private Intent getShareIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        return intent;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(
                getActivity(),
                Uri.withAppendedPath(MovieColumns.CONTENT_URI, "moviedb/" + String.valueOf(mMovieDBId)),
                MOVIE_COLUMNS,
                MovieColumns.TABLE_NAME + "." + MovieColumns.MOVIE_MOVIEDB_ID + "="+ String.valueOf(mMovieDBId),
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
        Log.i(Constants.APP_NAME, "loader finished");
        if (cursor != null && !cursor.moveToFirst()){
            Log.i(Constants.APP_NAME,"loader finished: no records returned");
            return;
        }
        Log.i(Constants.APP_NAME, String.valueOf(cursor.getCount()) + "  records returned");

        MovieCursor movieCursor = new MovieCursor(cursor);
        movieCursor.moveToFirst();
        final String backdropPath = Constants.BACKDROP_BASE_PATH + movieCursor.getBackdropPath();
        final String posterPath   = Constants.POSTER_BASE_PATH + movieCursor.getPosterPath();
        String title        = movieCursor.getTitle();
        long releaseDate    = movieCursor.getReleaseDate();
        String language     = movieCursor.getOriginalLan();
        Double voteAverage  = movieCursor.getVoteAverage();
        int voteCount       = movieCursor.getVoteCount();
        String overview     = movieCursor.getOverview();
        final Context context = getActivity();

        // force picasso to load image from cache first. If failed, try loading from network.
        Picasso.with(context)
                .load(backdropPath)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.backdrop_loading_placeholder)
                .error(R.drawable.backdrop_failed_placeholder)
                .fit()
                .centerCrop()
                .into(mMovieBackdrop, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        if(Utilities.isNetworkAvailable(context)) {
                            Picasso.with(context)
                                    .load(backdropPath)
                                    .placeholder(R.drawable.backdrop_loading_placeholder)
                                    .error(R.drawable.backdrop_failed_placeholder)
                                    .fit()
                                    .centerCrop()
                                    .into(mMovieBackdrop);
                        }
                    }
                });

        // force picasso to load image from cache first. If failed, try loading from network.
        Picasso.with(context)
                .load(posterPath)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.poster_loading_placeholder)
                .error(R.drawable.poster_failed_placeholder)
                .fit()
                .centerCrop()
                .into(mMoviePoster, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        if (Utilities.isNetworkAvailable(context)) {
                            Picasso.with(context)
                                    .load(posterPath)
                                    .placeholder(R.drawable.poster_loading_placeholder)
                                    .error(R.drawable.poster_failed_placeholder)
                                    .fit()
                                    .centerCrop()
                                    .into(mMoviePoster);
                        }
                    }
                });
        mMovieTitle.setText(title);
        mMovieReleaseDate.setText(Parser.humanDateStringFromMiliseconds(releaseDate));
        mOriginalLanguage.setText(language);
        mRatingText.setText(String.valueOf(voteAverage) + "/10");
        mVoteCount.setText(String.valueOf(voteCount) + " votes");
        mOverview.setText(overview);

        FavoriteCursor favoriteCursor = new FavoriteCursor(cursor);
        setFavoriteCheckbox(favoriteCursor);

        mFavoriteCheckbox.setTag(mMovieDBId);
        setFavoriteCheckboxOnClick();

        ReviewCursor reviewCursor = new ReviewCursor(cursor);
        showAllReviewRecords(reviewCursor);

        TrailerCursor trailerCursor = new TrailerCursor(cursor);
        String firstTrailerYoutubeId = showAllTrailerRecords(trailerCursor);

        // Set the share intent
        if(mShareActionProvider != null) {
            String shareText = "Checkout this movie " + title;
            if(firstTrailerYoutubeId != null)
                shareText += ". " + Constants.YOUTUBE_URL + firstTrailerYoutubeId;

            mShareActionProvider.setShareIntent(getShareIntent(shareText));
        }
    }

    private void setFavoriteCheckbox (FavoriteCursor favoriteCursor) {
        boolean checked = false;
        favoriteCursor.moveToFirst();

        do {
            try {
                if(favoriteCursor.getId() >= 0) {
                    checked = true;
                    break;
                }
            } catch (NullPointerException e) {}

        } while (favoriteCursor.moveToNext());

        mFavoriteCheckbox.setChecked(checked);
    }

    private void setFavoriteCheckboxOnClick () {
        mFavoriteCheckbox.setOnClickListener(createFavoriteCheckboxOnClickListener());
    }

    private String showAllTrailerRecords(TrailerCursor trailerCursor) {
        mTrailerSection.removeAllViews();

        Set<String> trailerSet = new LinkedHashSet<>();
        trailerCursor.moveToFirst();
        do {
            try {
                trailerSet.add(trailerCursor.getSource());
            } catch (NullPointerException e){}
        } while (trailerCursor.moveToNext());

        if(trailerSet.size() > 0) {
            TextView trailerTitle = new TextView(getActivity());
            trailerTitle.setText(R.string.trailers_title);

            LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textViewLayoutParams.setMargins(
                    Utilities.pixelSizeFromDp(getActivity(),25),
                    0,
                    0,
                    Utilities.pixelSizeFromDp(getActivity(),10));
            trailerTitle.setLayoutParams(textViewLayoutParams);
            //noinspection deprecation
            trailerTitle.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);

            mTrailerSection.addView(trailerTitle);

            View trailerPagerContainer = getLayoutInflater(null).inflate(R.layout.trailer_view_pager, mTrailerSection, true);
            mTrailerPager = (ViewPager) trailerPagerContainer.findViewById(R.id.view_pager);
            mTrailerPagerAdapter = new TrailerPagerAdapter(getActivity(), new ArrayList<String>(trailerSet));
            mTrailerPager.setAdapter(mTrailerPagerAdapter);

            mPagerIndicator = new PagerIndicator(getActivity(),null);
            mPagerIndicator.init(trailerSet.size());
            mPagerIndicator.setPagerController(this);
            mTrailerSection.addView(mPagerIndicator);

            mTrailerPager.addOnPageChangeListener(createPageChangedListener());
        } else {
            TextView trailerNAText = new TextView(getActivity());
            trailerNAText.setText(getString(R.string.no_trailers_available));

            LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textViewLayoutParams.setMargins(
                    Utilities.pixelSizeFromDp(getActivity(),25),
                    0,
                    0,
                    0);
            trailerNAText.setLayoutParams(textViewLayoutParams);

            mTrailerSection.addView(trailerNAText);
        }

        if(!trailerSet.isEmpty()) {
            return trailerSet.iterator().next();
        }

        return null;
    }

    private void showAllReviewRecords(ReviewCursor reviewCursor) {
        mReviewSection.removeAllViews();

        Set<String> reviewSet = new HashSet<>();
        reviewCursor.moveToFirst();
        do {
            try {
                String reviewUrl = reviewCursor.getUrl();
                if(reviewUrl != null && reviewSet.add(reviewUrl))
                    showCurrentReviewRecord(reviewCursor);
            } catch (NullPointerException e){}
        } while (reviewCursor.moveToNext());

        if(mReviewSection.getChildCount() > 0) {
            TextView reviewTitle = new TextView(getActivity());
            reviewTitle.setText(getString(R.string.reviews_title));
            LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textViewLayoutParams.setMargins(Utilities.pixelSizeFromDp(getActivity(), 15), 0, 0, Utilities.pixelSizeFromDp(getActivity(), 10));
            reviewTitle.setLayoutParams(textViewLayoutParams);
            //noinspection deprecation
            reviewTitle.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);

            mReviewSection.addView(reviewTitle, 0);
        } else {
            TextView reviewNAText = new TextView(getActivity());
            reviewNAText.setText(getString(R.string.no_reviews_available));
            LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textViewLayoutParams.setMargins(
                    Utilities.pixelSizeFromDp(getActivity(), 15),
                    0,
                    0,
                    Utilities.pixelSizeFromDp(getActivity(), 20));
            reviewNAText.setLayoutParams(textViewLayoutParams);

            mReviewSection.addView(reviewNAText);
        }
    }

    private void showCurrentReviewRecord(ReviewCursor reviewCursor) {
        @SuppressLint("InflateParams") View reviewItemView = getActivity().getLayoutInflater().inflate(R.layout.review_item, null);
        TextView authorTextView = (TextView) reviewItemView.findViewById(R.id.review_author);
        TextView contentTextView = (TextView) reviewItemView.findViewById(R.id.review_text);
        mReviewSection.addView(reviewItemView);

        authorTextView.setText(reviewCursor.getAuthor());
        contentTextView.setText(reviewCursor.getContent());
    }

    public void showDetailsByMovieDBId(long movieDBId) {
        mMovieDBId = movieDBId;
        startMovieDetailsIntentService();
        getLoaderManager().restartLoader(DETAIL_LOADER, null, this);
    }

    private void startMovieDetailsIntentService () {
        Context appContext = getActivity().getApplicationContext();
        Intent intent = new Intent(appContext, MovieDetailsIntentService.class);
        intent.putExtra(Constants.MOVIEDB_ID, mMovieDBId);
        appContext.startService(intent);
    }

    private ViewPager.OnPageChangeListener createPageChangedListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int page = position + 1;
                if(mPagerIndicator.getCheckedRadioButtonId() != page)
                    mPagerIndicator.check(page);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    @Override
    public void setPage(int page) {
        int actualPage = (page - 1) >= 0 ? page - 1 : 0 ;
        if(mTrailerPager.getCurrentItem() != actualPage)
            mTrailerPager.setCurrentItem(actualPage);
    }

    private View.OnClickListener createFavoriteCheckboxOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long movieDBId = (long) v.getTag();
                CheckBox favoriteCheckbox = (CheckBox) v;

                if (favoriteCheckbox.isChecked()) {
                    FavoriteContentValues favoriteContentValues = new FavoriteContentValues();
                    favoriteContentValues.putFavoriteMoviedbId(movieDBId);
                    favoriteContentValues.putCreated(Utilities.getCurrentTimeInMillis());
                    mContext.getContentResolver().insert(FavoriteColumns.CONTENT_URI, favoriteContentValues.values());
                    Toast.makeText(mContext, "Added to favourite", Toast.LENGTH_SHORT).show();
                } else {
                    FavoriteSelection selection = new FavoriteSelection();
                    selection.favoriteMoviedbId(movieDBId);
                    mContext.getContentResolver().delete(FavoriteColumns.CONTENT_URI, selection.sel(), selection.args());
                    Toast.makeText(mContext, "Removed from favourite", Toast.LENGTH_SHORT).show();
                }
                mContext.getApplicationContext().getContentResolver().notifyChange(Uri.withAppendedPath(MovieColumns.CONTENT_URI, "with_favorite"), null);
            }
        };
    }
}
