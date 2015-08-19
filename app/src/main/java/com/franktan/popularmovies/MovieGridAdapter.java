package com.franktan.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.franktan.popularmovies.util.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by tan on 12/08/2015.
 */
public class MovieGridAdapter extends CursorAdapter {
    private Context mContext;

    public MovieGridAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.grid_element, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        bindView(view,context,cursor);
        return view;
    }

//    public int getCount() {
//        //TODO;
//        return 10;
//    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String posterPath = cursor.getString(MoviesGridFragment.COL_POSTER_PATH);
        String postFullPath = Constants.POSTER_BASE_PATH + posterPath;
        Log.i(Constants.APP_NAME,postFullPath);

        Picasso.with(context).load(postFullPath).into(viewHolder.posterImage);
    }

    public static class ViewHolder {
        public final ImageView posterImage;

        public ViewHolder(View view) {
            posterImage = (ImageView) view.findViewById(R.id.poster_image);
        }
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//        if (convertView == null) {
//            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.MATCH_PARENT));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        //imageView.setImageResource(mThumbIds[position]);
//        return imageView;
//    }
}
