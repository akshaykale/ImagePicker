package com.akshaykale.imagepicker;

import java.util.ArrayList;

/**
 * Created by akshay.kale on 08/09/2017.
 */

interface IDevicePhotoListener {

    void onPhotosLoaded(ArrayList<PhotoObject> photos);
}
