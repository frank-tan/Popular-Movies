package com.franktan.popularmovies.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.franktan.popularmovies.model.MovieGroup;
import com.franktan.popularmovies.ui.fragments.MoviesGridFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tan on 14/09/2015.
 */
public class MovieGroupViewPagerAdapter extends FragmentPagerAdapter {

    List<MovieGroup> mMovieGroupList = new ArrayList<>();

    public MovieGroupViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addMovieGroup (MovieGroup movieGroup) {
        mMovieGroupList.add(movieGroup);
    }

    @Override
    public int getCount() {
        return mMovieGroupList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return MoviesGridFragment.newInstance(-1, mMovieGroupList.get(position));
    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return mMovieGroupList.get(position).getTitle();
    }
}
