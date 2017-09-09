package com.akshaykale.imagepicker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by akshay.kale on 08/09/2017.
 */

public class ImagePickerFragment extends DialogFragment implements IDevicePhotoListener, IPhotoClickListeners {

    private static final int REQUEST_IMAGE_CAPTURE = 121;
    private RecyclerView recyclerView;
    private ImagePickerRecyclerViewAdapter imagePickerRecyclerViewAdapter;

    private static final int REQUEST_WRITE_PERMISSION = 786;

    ImagePickerListener imagePickerListener = null;

    private boolean DISABLE_CAMERA_DEFAULT = false;

    ArrayList<PhotoObject> photos = new ArrayList<>();
    private String TAG = getClass().getSimpleName();

    ImageLoadEngine imageLoadEngine = new ImageLoad();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lib_image_picker, container, false);
        WindowManager.LayoutParams wmlp = getDialog().getWindow().getAttributes();
        wmlp.gravity = Gravity.FILL_HORIZONTAL;
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_imagepicker_gallery);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requestPermission();

        createAndLoadCameraObject();

        createAndLoadLoadingObject();

        setupRecyclerView();
    }

    private void createAndLoadLoadingObject() {
        PhotoObject cameraObject = new PhotoObject(EItemType.LOADING);
        photos.add(1, cameraObject);
        photos.add(2, cameraObject);
        photos.add(3, cameraObject);
        photos.add(4, cameraObject);
        photos.add(5, cameraObject);
        photos.add(6, cameraObject);
        photos.add(7, cameraObject);
        photos.add(8, cameraObject);
        photos.add(9, cameraObject);
        photos.add(10, cameraObject);
        photos.add(11, cameraObject);
        photos.add(12, cameraObject);

    }

    private void createAndLoadCameraObject() {
        PhotoObject cameraObject = new PhotoObject(EItemType.CAMERA);
        photos.add(0, cameraObject);
    }

    private void setupRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        imagePickerRecyclerViewAdapter = new ImagePickerRecyclerViewAdapter(photos, getContext(), this, imageLoadEngine);
        recyclerView.setAdapter(imagePickerRecyclerViewAdapter);
    }

    private void loadLocalGalleryImages() {
        GetDevicePhotosTask getDevicePhotosTask = new GetDevicePhotosTask(getContext(), this);
        getDevicePhotosTask.execute();
    }

    @Override
    public void onPhotosLoaded(ArrayList<PhotoObject> photos) {
        this.photos.remove(12);
        this.photos.remove(11);
        this.photos.remove(10);
        this.photos.remove(9);
        this.photos.remove(8);
        this.photos.remove(7);
        this.photos.remove(6);
        this.photos.remove(5);
        this.photos.remove(4);
        this.photos.remove(3);
        this.photos.remove(2);
        this.photos.remove(1);
        this.photos.addAll(photos);//
        imagePickerRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPhotoClick(PhotoObject photo) {
        switch (photo.eItemType) {
            case CAMERA: {
                if (!DISABLE_CAMERA_DEFAULT)
                    openCamera();
                else
                    imagePickerListener.onCameraClicked(null);
            }
            break;
            case LOADING:
                break;
            case PHOTO:
            case VIDEO: {
                if (imagePickerListener == null) {
                    return;
                } else {
                    imagePickerListener.onPhotoClicked(photo);
                }
                Log.d(TAG, photo.name);
                break;
            }
        }
    }

    private void openCamera() {
        Log.d(TAG, "Opening Camera");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            saveImageToGallery(image, System.currentTimeMillis() + ".jpg");

            imagePickerListener.onCameraClicked(image);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadLocalGalleryImages();
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            loadLocalGalleryImages();
        }
    }


    /**
     * Functions
     */

    public void addOnClickListener(ImagePickerListener listener) {
        this.imagePickerListener = listener;
    }

    public void saveImageToGallery(Bitmap bitmap, String name) {
        //Save to internal storage
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, name, "");
        }
    }

    public void disableDefaultCameraFunction(boolean status){
        DISABLE_CAMERA_DEFAULT = status;
    }

    public void setImageLoadEngine(ImageLoadEngine engine){
        imageLoadEngine = engine;
    }
}
