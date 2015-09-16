package com.franktan.popularmovies.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.franktan.popularmovies.R;
import com.franktan.popularmovies.model.MovieGroup;
import com.franktan.popularmovies.sync.MovieSyncAdapter;
import com.franktan.popularmovies.ui.adapters.MovieGroupViewPagerAdapter;
import com.franktan.popularmovies.ui.fragments.MovieDetailFragment;
import com.franktan.popularmovies.ui.fragments.MoviesGridFragment;

public class MovieGridActivity extends AppCompatActivity
        implements MoviesGridFragment.OnFragmentInteractionListener {

    private boolean mTwoPaneMode;
    private ViewPager mMovieGroupViewPager;
    private MovieGroupViewPagerAdapter mMovieGroupViewAdapter;
    private TabLayout mTabLayout;
    private int mNumOfTabs = 3;
    private CharSequence mTabTitles[] = {"Popular", "Top Rated", "Favourite"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);
        setSupportActionBar((Toolbar) findViewById(R.id.movie_group_toolbar));

        MovieSyncAdapter.initialize(this);

//        if(findViewById(R.id.fragment_movie_details) != null) {
//            mTwoPaneMode = true;
//        } else {
//            mTwoPaneMode = false;
//        }

        mMovieGroupViewAdapter = new MovieGroupViewPagerAdapter(getSupportFragmentManager());
        mMovieGroupViewAdapter.addMovieGroup(MovieGroup.POPULAR);
        mMovieGroupViewAdapter.addMovieGroup(MovieGroup.TOP_RATED);
        mMovieGroupViewAdapter.addMovieGroup(MovieGroup.FAVORITE);

        mMovieGroupViewPager = (ViewPager) findViewById(R.id.movie_grid_view_pager);
        mMovieGroupViewPager.setAdapter(mMovieGroupViewAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mMovieGroupViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
}
