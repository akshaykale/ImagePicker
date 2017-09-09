package com.akshaykale.imagepicker;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by akshaykale on 2017/09/09.
 */

class ImageLoad implements ImageLoadEngine {
    private RequestOptions options;

    public ImageLoad() {
        options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.drawable.placeholder);
    }

    @Override
    public void loadImage(String uri, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(uri)
                .thumbnail(0.1f)
                .apply(options)
                .into(imageView);
    }
}
