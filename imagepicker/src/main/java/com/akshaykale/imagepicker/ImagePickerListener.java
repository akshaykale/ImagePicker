package com.akshaykale.imagepicker;

import android.graphics.Bitmap;

/**
 * Created by akshaykale on 2017/09/09.
 */

public interface ImagePickerListener {
    public void onPhotoClicked(PhotoObject photoObject);
    public void onCameraClicked(Bitmap image);
}
