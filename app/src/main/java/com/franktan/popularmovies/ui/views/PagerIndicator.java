package com.franktan.popularmovies.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.franktan.popularmovies.R;

/**
 * Created by tan on 8/09/2015.
 */
public class PagerIndicator extends RadioGroup {
    final Context mContext;
    SetPage mPager;

    public PagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    /**
     * Create number of tab indicators (radio buttons) provided by size and set the first one as checked
     * @param size number of tab indicators
     */
    public void init (int size) {

        for (int i = 0; i < size; i++) {
            RadioButton radioButton = new RadioButton(mContext);
            radioButton.setId(i + 1);
            radioButton.setButtonDrawable(R.drawable.page_indicator_radiobutton_selector);

            addView(radioButton);
        }
        //noinspection ResourceType
        check(1);
    }

    public void setPagerController(SetPage pager) {
        mPager = pager;

        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mPager.setPage(checkedId);
            }
        });
    }

    public interface SetPage {
        void setPage (int page);
    }
}
