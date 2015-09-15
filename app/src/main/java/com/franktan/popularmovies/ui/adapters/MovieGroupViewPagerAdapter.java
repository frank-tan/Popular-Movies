package com.franktan.popularmovies.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.franktan.popularmovies.ui.fragments.MoviesGridFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tan on 14/09/2015.
 */
public class MovieGroupViewPagerAdapter extends FragmentPagerAdapter {

    CharSequence[] mTitles = new CharSequence[10];
    List<MoviesGridFragment> mMoviesGridFragment = new ArrayList<>();

    public MovieGroupViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addMoviesGridFragment (MoviesGridFragment moviesGridFragment, String title) {
        int oldSize = mMoviesGridFragment.size();
        mMoviesGridFragment.add(moviesGridFragment);
        mTitles[oldSize] = title;
    }

    @Override
    public int getCount() {
        return mMoviesGridFragment.size();
    }

    @Override
    public Fragment getItem(int position) {
        //// TODO: 15/09/2015 refactor code to create fragment here 
        return mMoviesGridFragment.get(position);
    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
