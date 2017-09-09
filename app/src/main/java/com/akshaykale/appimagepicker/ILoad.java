package com.akshaykale.appimagepicker;

import android.widget.ImageView;

import com.akshaykale.imagepicker.ImageLoadEngine;
import com.bumptech.glide.Glide;

/**
 * Created by akshaykale on 2017/09/09.
 */

public class ILoad implements ImageLoadEngine {
    @Override
    public void loadImage(String uri, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(uri)
                .into(imageView);
    }
}
