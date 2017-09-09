package com.akshaykale.imagepicker;

import android.graphics.Bitmap;

/**
 * Created by akshay.kale on 08/09/2017.
 */

public class PhotoObject {

    protected EItemType eItemType;

    String name;
    String path;
    String created_timestamp;
    String picasa_id;
    double lat,lng;

    public PhotoObject(EItemType eItemType) {
        this.eItemType = eItemType;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getCreated_timestamp() {
        return created_timestamp;
    }

    public String getPicasa_id() {
        return picasa_id;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}

