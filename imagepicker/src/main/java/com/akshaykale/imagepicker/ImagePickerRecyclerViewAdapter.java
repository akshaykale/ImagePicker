package com.akshaykale.imagepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by akshaykale on 2017/08/20.
 */

public class ImagePickerRecyclerViewAdapter extends RecyclerView.Adapter<ImagePickerRecyclerViewAdapter.PhotosRecyclerViewHolder> {

    ArrayList<PhotoObject> photos;
    Context context;
    private IPhotoClickListeners photoClickListeners;

    public ImagePickerRecyclerViewAdapter(ArrayList<PhotoObject> photos, Context context, IPhotoClickListeners photoClickListeners) {
        this.photos = photos;
        this.context = context;
        this.photoClickListeners = photoClickListeners;
    }

    @Override
    public PhotosRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.recycler_view_posts_layout,parent,false);

        PhotosRecyclerViewHolder viewHolder1 = new PhotosRecyclerViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(final PhotosRecyclerViewHolder holder, final int position) {
        holder.bindClickListener(photos.get(position),photoClickListeners);

        String uri = photos.get(position).path;

        File imgFile = new  File(uri);

        if(imgFile.exists()){
            Bitmap myBitmap = Utils.decodeSampledBitmap(uri, 500, 500);
            holder.image.setImageBitmap(myBitmap);
        }
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


    public class PhotosRecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ImageView image;

        public PhotosRecyclerViewHolder(View v) {

            super(v);
            image = (ImageView) v.findViewById(R.id.recycler_view_photo_image);
            //textView = (TextView) v.findViewById(R.id.textview1);

        }

        public void bindClickListener(final PhotoObject photo, final IPhotoClickListeners photoClickListeners){
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    photoClickListeners.onPhotoClick(photo);
                }
            });
        }
    }
}
