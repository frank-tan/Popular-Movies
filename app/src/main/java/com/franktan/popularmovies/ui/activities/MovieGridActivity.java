package com.franktan.popularmovies.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.BuildConfig;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.franktan.popularmovies.R;
import com.franktan.popularmovies.model.MovieGroup;
import com.franktan.popularmovies.sync.MovieSyncAdapter;
import com.franktan.popularmovies.ui.adapters.MovieGroupViewPagerAdapter;
import com.franktan.popularmovies.ui.fragments.MovieDetailFragment;
import com.franktan.popularmovies.ui.fragments.MoviesGridFragment;
import com.squareup.picasso.Picasso;

public class MovieGridActivity extends AppCompatActivity
        implements MoviesGridFragment.OnFragmentInteractionListener {

    private boolean mTwoPaneMode;
    ViewPager mMovieGroupViewPager;
    MovieGroupViewPagerAdapter mMovieGroupViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabLayout tabLayout;

        setContentView(R.layout.activity_movie_grid);
        setSupportActionBar((Toolbar) findViewById(R.id.movie_group_toolbar));

        MovieSyncAdapter.initialize(this);

        mTwoPaneMode = (findViewById(R.id.fragment_movie_details) != null) ? true : false;

        if (BuildConfig.DEBUG) {
            Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
            Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        }

        mMovieGroupViewAdapter = new MovieGroupViewPagerAdapter(getSupportFragmentManager());
        mMovieGroupViewAdapter.addMovieGroup(MovieGroup.POPULAR);
        mMovieGroupViewAdapter.addMovieGroup(MovieGroup.TOP_RATED);
        mMovieGroupViewAdapter.addMovieGroup(MovieGroup.FAVORITE);

        mMovieGroupViewPager = (ViewPager) findViewById(R.id.movie_grid_view_pager);
        mMovieGroupViewPager.setAdapter(mMovieGroupViewAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mMovieGroupViewPager);
    }

    @Override
    public boolean isInTwoPaneMode() {
        return mTwoPaneMode;
    }

    @Override
    public void onMovieItemSelected(long movieDBId) {
        MovieDetailFragment fragment = (MovieDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_movie_details);
        fragment.showDetailsByMovieDBId(movieDBId);
    }

    @Override
    public boolean isActiveFragment(MoviesGridFragment fragment) {
        int activePosition = mMovieGroupViewPager.getCurrentItem();
        MovieGroup activeMovieGroup = mMovieGroupViewAdapter.getMovieGroup(activePosition);
        MovieGroup fragmentMovieGroup = fragment.getMovieGroup();
        return (activeMovieGroup == fragmentMovieGroup);
    }
}
