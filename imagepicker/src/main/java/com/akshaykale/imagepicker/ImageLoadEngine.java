package com.akshaykale.imagepicker;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by akshaykale on 2017/09/09.
 */

public interface ImageLoadEngine {
    void loadImage(String uri, ImageView imageView);
}
