package com.franktan.popularmovies.ui.fragments;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.franktan.popularmovies.R;
import com.franktan.popularmovies.util.Constants;
import com.franktan.popularmovies.util.Utilities;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tan on 5/09/2015.
 */
public class TrailerPagerAdapter extends PagerAdapter {

    private List<String> mTrailerYoutubeIds;
    private List<View> mTrailerViews;
    private Map<YouTubeThumbnailView, YouTubeThumbnailLoader> mThumbnailViewToLoaderMap;
    private TrailerThumbnailListener mTrailerThumbnailListener;
    private LayoutInflater mInflater;
    private Context mContext;

    public TrailerPagerAdapter(Context context, List<String> mTrailerYoutubeIds) {
        this.mContext = context;
        this.mTrailerYoutubeIds = mTrailerYoutubeIds;
        this.mInflater = LayoutInflater.from(context);

        this.mThumbnailViewToLoaderMap = new HashMap<YouTubeThumbnailView, YouTubeThumbnailLoader>();
    }

    @Override
    public int getCount() {
        return mTrailerYoutubeIds.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i(Constants.APP_NAME,"instantiateItem called - position: "+position);
        // inflate a new page
        View view = mInflater.inflate(R.layout.trailer_item,container,false);

        YouTubeThumbnailView youTubeThumbnailView = (YouTubeThumbnailView) view.findViewById(R.id.youtube_thumbnail);
        youTubeThumbnailView.setTag(mTrailerYoutubeIds.get(position));
        youTubeThumbnailView.initialize(Utilities.getGoogleApiKey(mContext), new TrailerThumbnailListener());

        container.addView(view);

        return view;
    }

    public void releaseLoaders() {
        for (YouTubeThumbnailLoader loader : mThumbnailViewToLoaderMap.values()) {
            loader.release();
        }
    }

    private final class TrailerThumbnailListener implements
            YouTubeThumbnailView.OnInitializedListener,
            YouTubeThumbnailLoader.OnThumbnailLoadedListener {

        @Override
        public void onInitializationSuccess(
                YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
            Log.i(Constants.APP_NAME,"TrailerPagerAdapter - onInitializationSuccess");
            loader.setOnThumbnailLoadedListener(this);
            mThumbnailViewToLoaderMap.put(view, loader);
            //// TODO: 5/09/2015: load a place holder image
            //view.setImageResource(R.drawable.loading_thumbnail);
            loader.setVideo((String)view.getTag());
        }

        @Override
        public void onInitializationFailure(
                YouTubeThumbnailView view, YouTubeInitializationResult loader) {
            Log.i(Constants.APP_NAME,"TrailerPagerAdapter - onInitializationFailure");
            //// TODO: 5/09/2015: load backdrop image
            //view.setImageResource(R.drawable.no_thumbnail);
        }

        @Override
        public void onThumbnailLoaded(YouTubeThumbnailView view, String videoId) {
            Log.i(Constants.APP_NAME, "TrailerPagerAdapter - onThumbnailLoaded");
            //// TODO: 6/09/2015 show play button
        }

        @Override
        public void onThumbnailError(YouTubeThumbnailView view, YouTubeThumbnailLoader.ErrorReason errorReason) {
            Log.i(Constants.APP_NAME,"TrailerPagerAdapter - onThumbnailError");
            //// TODO: 5/09/2015: load backdrop image
            //view.setImageResource(R.drawable.no_thumbnail);
        }
    }
}
