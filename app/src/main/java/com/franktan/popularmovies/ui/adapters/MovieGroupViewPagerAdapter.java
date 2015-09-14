package com.franktan.popularmovies.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.franktan.popularmovies.ui.fragments.MoviesGridFragment;

/**
 * Created by tan on 14/09/2015.
 */
public class MovieGroupViewPagerAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;
    CharSequence[] mTitles;

    public MovieGroupViewPagerAdapter(FragmentManager fm, int numOfTabs, CharSequence[] titles) {
        super(fm);
        mNumOfTabs = numOfTabs;
        mTitles = titles;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        return MoviesGridFragment.newInstance(position);
    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
