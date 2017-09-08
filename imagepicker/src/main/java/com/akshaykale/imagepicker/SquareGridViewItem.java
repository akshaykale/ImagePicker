package com.akshaykale.imagepicker;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by akshay.kale on 01/09/2017.
 */

public class SquareGridViewItem extends android.support.v7.widget.AppCompatImageView {
    public SquareGridViewItem(Context context) {
        super(context);
    }

    public SquareGridViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareGridViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
