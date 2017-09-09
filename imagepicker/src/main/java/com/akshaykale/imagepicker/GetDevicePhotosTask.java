package com.akshaykale.imagepicker;

import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by akshay.kale on 08/09/2017.
 */

class GetDevicePhotosTask extends AsyncTask<Void,Void,ArrayList<PhotoObject>> {

    private IDevicePhotoListener callback;
    private Context context;

    String[] columns = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.LATITUDE,
            MediaStore.Images.Media.LONGITUDE,
            MediaStore.Images.Media.PICASA_ID
    };

    String orderBy = MediaStore.Images.Media.DATE_TAKEN;
    Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    public GetDevicePhotosTask( Context context, IDevicePhotoListener callback) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected ArrayList<PhotoObject> doInBackground(Void... params) {
        ArrayList<PhotoObject> photoList = new ArrayList<>();
        try {
            Cursor cursor = context.getContentResolver().query(uri, columns, null, null, orderBy);
            assert cursor != null;
            int mCount = cursor.getCount();
            for (int i = mCount - 1; i >= 0; i--) {

                PhotoObject mPhoto = new PhotoObject(EItemType.PHOTO);

                cursor.moveToPosition(i);

                int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                int displayNameColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
                int picasaIdColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.PICASA_ID);
                //int thumbColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.MINI_THUMB_MAGIC);
                int latColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.LATITUDE);
                int lngColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.LONGITUDE);
                int timestampColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);


                mPhoto.path = cursor.getString(dataColumnIndex);

                mPhoto.name = cursor.getString(displayNameColumnIndex);

                if (picasaIdColumnIndex > 0)
                    mPhoto.picasa_id = cursor.getString(picasaIdColumnIndex);

                if (timestampColumnIndex > 0)
                    mPhoto.created_timestamp = String.valueOf(cursor.getInt(timestampColumnIndex));

                if (latColumnIndex > 0 && lngColumnIndex > 0) {
                    mPhoto.lat = cursor.getDouble(latColumnIndex);
                    mPhoto.lng = cursor.getDouble(lngColumnIndex);
                } else {
                    ExifInterface exif = new ExifInterface(mPhoto.path);
                    String lat = ExifInterface.TAG_GPS_LATITUDE;
                    String lat_data = exif.getAttribute(lat);
                    mPhoto.lat = locationConversion(lat_data);

                    String lng = ExifInterface.TAG_GPS_LONGITUDE;
                    String lng_data = exif.getAttribute(lng);
                    mPhoto.lng = locationConversion(lng_data);
                }

                //callback.onPhotoLoaded(mPhoto);
                photoList.add(mPhoto);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return photoList;
    }

    @Override
    protected void onPostExecute(ArrayList<PhotoObject> photos) {
        callback.onPhotosLoaded(photos);
    }

    public double locationConversion(String data){

        String[] aaa = data.split(",");

        String[] ds = aaa[0].split("/");
        String[] ms = aaa[1].split("/");
        String[] ss = aaa[2].split("/");

        double d = (double) Integer.parseInt(ds[0])/Integer.parseInt(ds[1]);
        double m = (double) Integer.parseInt(ms[0])/Integer.parseInt(ms[1]);
        double s = (double) Integer.parseInt(ss[0])/Integer.parseInt(ss[1]);

        return Math.signum(d) * (Math.abs(d) + (m / 60.0) + (s / 3600.0));
    }

}
