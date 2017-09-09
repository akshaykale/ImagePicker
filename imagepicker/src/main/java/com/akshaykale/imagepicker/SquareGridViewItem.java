package com.akshaykale.imagepicker;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by akshay.kale on 01/09/2017.
 */

class SquareGridViewItem extends android.support.v7.widget.AppCompatImageView {
    public SquareGridViewItem(Context context) {
        super(context);
        setScaleType(ScaleType.CENTER_CROP);
    }

    public SquareGridViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScaleType(ScaleType.CENTER_CROP);
    }

    public SquareGridViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.CENTER_CROP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
