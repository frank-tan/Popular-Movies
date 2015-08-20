package com.franktan.popularmovies;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by tan on 20/08/2015.
 */
public class PosterImageView extends ImageView {
    public PosterImageView(Context context)
    {
        super(context);
    }

    public PosterImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public PosterImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        float desiredAspect;
        Drawable content = getDrawable();
        if(content == null) {
            desiredAspect = 1;
        } else {
            int w = getDrawable().getIntrinsicWidth();
            int h = getDrawable().getIntrinsicHeight();
            if (w <= 0)
                w = 1;
            desiredAspect = (float) h / w;
        }
        setMeasuredDimension(measuredWidth, (int) (measuredWidth * desiredAspect));
    }
}
