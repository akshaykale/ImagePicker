package com.akshaykale.imagepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by akshay.kale on 08/09/2017.
 */

public class ImagePickerFragment extends DialogFragment implements IDevicePhotoListener, IPhotoClickListeners {

    private RecyclerView recyclerView;
    private ImagePickerRecyclerViewAdapter imagePickerRecyclerViewAdapter;

    ArrayList<PhotoObject> photos;
    private String TAG = getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lib_image_picker, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_imagepicker_gallery);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();

        loadLocalGalleryImages();
    }

    private void setupRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        imagePickerRecyclerViewAdapter = new ImagePickerRecyclerViewAdapter(photos, getContext(), this);
        recyclerView.setAdapter(imagePickerRecyclerViewAdapter);
    }

    private void loadLocalGalleryImages() {
        GetDevicePhotosTask getDevicePhotosTask = new GetDevicePhotosTask(getContext(), this);
    }


    @Override
    public void onPhotoLoaded(PhotoObject photo) {
        photos.add(photo);
        imagePickerRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPhotoClick(PhotoObject photo) {
        Log.d(TAG, photo.name);
    }
}
