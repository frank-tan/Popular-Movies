package com.franktan.popularmovies.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.franktan.popularmovies.model.MovieGroup;
import com.franktan.popularmovies.ui.fragments.MoviesGridFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes a list of movie groups and spits the fragments for the fragment view pager
 * Created by tan on 14/09/2015.
 */
public class MovieGroupViewPagerAdapter extends FragmentPagerAdapter {

    private List<MovieGroup> mMovieGroupList = new ArrayList<>();
    FragmentManager mFragmentManager;

    public MovieGroupViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    /**
     * Add new page for the view pager
     * @param movieGroup
     */
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
        return getMovieGroup(position).getTitle();
    }

    public MovieGroup getMovieGroup(int position) {
        return mMovieGroupList.get(position);
    }

    public void checkAllFragmentsForViewPageChange () {
        List<Fragment> fragments = mFragmentManager.getFragments();
        for(Fragment fragment: fragments) {
            if(fragment instanceof MoviesGridFragment && ((MoviesGridFragment) fragment).checkFragmentActive()) {
                break;
            }
        }
    }
}
