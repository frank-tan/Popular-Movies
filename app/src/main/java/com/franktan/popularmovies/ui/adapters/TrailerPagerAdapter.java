package com.franktan.popularmovies.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.franktan.popularmovies.R;
import com.franktan.popularmovies.util.Constants;
import com.franktan.popularmovies.util.Utilities;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tan on 5/09/2015.
 */
public class TrailerPagerAdapter extends PagerAdapter {

    private final List<String> mTrailerYoutubeIds;
    private final Map<YouTubeThumbnailView, YouTubeThumbnailLoader> mThumbnailViewToLoaderMap;
    private final LayoutInflater mInflater;
    private final Context mContext;

    public TrailerPagerAdapter(Context context, List<String> mTrailerYoutubeIds) {
        this.mContext = context;
        this.mTrailerYoutubeIds = mTrailerYoutubeIds;
        this.mInflater = LayoutInflater.from(context);

        this.mThumbnailViewToLoaderMap = new HashMap<>();
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
        String youtubeId = mTrailerYoutubeIds.get(position);

        YouTubeThumbnailView youTubeThumbnailView = (YouTubeThumbnailView) view.findViewById(R.id.youtube_thumbnail);
        youTubeThumbnailView.setTag(youtubeId);
        youTubeThumbnailView.initialize(Utilities.getGoogleApiKey(mContext), new TrailerThumbnailListener());

        ImageView playButton = (ImageView) view.findViewById(R.id.youtube_play_button);
        playButton.setTag(youtubeId);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) mContext;
                String youtubeId = (String) v.getTag();
                Intent intent = YouTubeStandalonePlayer.createVideoIntent(
                        activity, Utilities.getGoogleApiKey(mContext), youtubeId, 0, true, false);

                if (intent != null) {
                    if (canResolveIntent(intent)) {
                        activity.startActivity(intent);
                    } else {
                        // Youtube not available. Open the youtube video url instead
                        String url = Constants.YOUTUBE_URL + youtubeId;
                        Intent intentURL = new Intent(Intent.ACTION_VIEW);
                        intentURL.setData(Uri.parse(url));
                        activity.startActivity(intentURL);
                    }
                }
            }
        });

        container.addView(view);

        return view;
    }

    public void releaseLoaders() {
        for (YouTubeThumbnailLoader loader : mThumbnailViewToLoaderMap.values()) {
            loader.release();
        }
    }

    private boolean canResolveIntent(Intent intent) {
        List<ResolveInfo> resolveInfo = mContext.getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfo != null && !resolveInfo.isEmpty();
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
        }

        @Override
        public void onThumbnailError(YouTubeThumbnailView view, YouTubeThumbnailLoader.ErrorReason errorReason) {
            Log.i(Constants.APP_NAME,"TrailerPagerAdapter - onThumbnailError");
            //// TODO: 5/09/2015: load backdrop image
            //view.setImageResource(R.drawable.no_thumbnail);
        }
    }
}
