package com.franktan.popularmovies.ui.fragments;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.franktan.popularmovies.R;
import com.franktan.popularmovies.data.movie.MovieCursor;
import com.franktan.popularmovies.util.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by tan on 12/08/2015.
 */
public class MovieGridAdapter extends CursorAdapter {

    public MovieGridAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.grid_element, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        MovieCursor movieCursor = new MovieCursor(cursor);
        String posterPath = movieCursor.getPosterPath();
        String posterFullPath = Constants.POSTER_BASE_PATH + posterPath;

        Picasso.with(context)
                .load(posterFullPath)
                .placeholder(R.drawable.poster_loading_placeholder)
                .error(R.drawable.poster_failed_placeholder)
                .fit()
                .centerCrop()
                .into(viewHolder.posterImage);
    }

    public static class ViewHolder {
        public final ImageView posterImage;

        public ViewHolder(View view) {
            posterImage = (ImageView) view.findViewById(R.id.poster_image);
        }
    }

    //    public int getCount() {
//
//        return 10;
//    }

//    public Object getItem(int position) {
//        return ;
//    }

    public long getItemId(int position) {
        if(mCursor != null) {
            if(mCursor.moveToPosition(position)) {
                MovieCursor movieCursor = new MovieCursor(mCursor);
                return movieCursor.getMovieMoviedbId();
            }
        }
        return 0;
    }

}
