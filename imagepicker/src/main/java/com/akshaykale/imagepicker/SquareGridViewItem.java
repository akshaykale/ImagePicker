package com.akshaykale.imagepicker;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by akshay.kale on 01/09/2017.
 */

class SquareGridViewItem extends android.support.v7.widget.AppCompatImageView {
    public SquareGridViewItem(Context context) {
        super(context);
        setUp();
    }

    public SquareGridViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public SquareGridViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private void setUp(){
        setScaleType(ScaleType.CENTER_CROP);
    }
}
