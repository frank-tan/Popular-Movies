package com.franktan.popularmovies.ui.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.franktan.popularmovies.R;
import com.franktan.popularmovies.util.Constants;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

public class MovieDetailActivity
        extends AppCompatActivity
        implements ObservableScrollViewCallbacks {

    private View mBackdropImageView;
    private View mToolbarView;
    private ObservableScrollView mObservableScrollView;
    private int mParallaxImageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mBackdropImageView = findViewById(R.id.movie_backdrop);
        mToolbarView = findViewById(R.id.toolbar);
        mObservableScrollView = (ObservableScrollView) findViewById(R.id.scroll_view);

        mToolbarView.setBackgroundColor(
                ScrollUtils.getColorWithAlpha(0, ContextCompat.getColor(this, R.color.popularmovies_primary)));
        mObservableScrollView.setScrollViewCallbacks(this);

        mParallaxImageHeight = getResources().getDimensionPixelSize(
                R.dimen.backdrop_parallax_image_height);

        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (NullPointerException e) {
            Log.i(Constants.APP_NAME,"MovieDetailActivity: Cannot setDisplayShowTitleEnabled(false)");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Restore the state of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mObservableScrollView.getCurrentScrollY(), false, false);
    }

    /**
     * Set the parallax image position and transparency of the tool bar
     * @param scrollY
     * @param firstScroll
     * @param dragging
     */
    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = ContextCompat.getColor(this,R.color.popularmovies_primary);
        float alpha = (float) scrollY / mParallaxImageHeight;
        alpha = (alpha > 0.85F) ? 0.85F : alpha;
        alpha = (alpha < 0) ? 0 : alpha;
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        mBackdropImageView.setTranslationY(scrollY/2);
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    @Override
    public void onDownMotionEvent() {
    }
}
