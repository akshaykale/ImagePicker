package com.akshaykale.appimagepicker;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.akshaykale.imagepicker.ImagePickerFragment;
import com.akshaykale.imagepicker.ImagePickerListener;
import com.akshaykale.imagepicker.PhotoObject;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements ImagePickerListener, View.OnClickListener {

    private ImagePickerFragment imagePickerFragment;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        imageView = (ImageView) findViewById(R.id.imageview);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            imagePickerFragment = new ImagePickerFragment();//.newInstance(mStackLevel);
            imagePickerFragment.addOnClickListener(this);
            //imagePickerFragment.setImageLoadEngine(new ILoad());
            imagePickerFragment.show(ft, "dialog");
        }
    }

    @Override
    public void onPhotoClicked(PhotoObject photoObject) {
        Glide.with(getApplicationContext()).clear(imageView);
        Glide.with(getApplicationContext())
                .load(photoObject.getPath())
                .into(imageView);
        imagePickerFragment.dismiss();
    }

    @Override
    public void onCameraClicked(Bitmap image) {
        imageView.setImageBitmap(image);
        imagePickerFragment.dismiss();
    }
}
