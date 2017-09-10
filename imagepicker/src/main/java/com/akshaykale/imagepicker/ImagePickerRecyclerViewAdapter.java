package com.akshaykale.imagepicker;
/**
 MIT License

 Copyright (c) 2017 Akshay Kale

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 * */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by akshaykale on 2017/08/20.
 */

class ImagePickerRecyclerViewAdapter extends RecyclerView.Adapter<ImagePickerRecyclerViewAdapter.PhotosRecyclerViewHolder> {

    ArrayList<PhotoObject> photos;
    Context context;
    private IPhotoClickListeners photoClickListeners;
    private int thumbFactor = 2;
    ImageLoadEngine imageLoad;

    public ImagePickerRecyclerViewAdapter(ArrayList<PhotoObject> photos, Context context, IPhotoClickListeners photoClickListeners, ImageLoadEngine engine) {
        this.photos = photos;
        this.context = context;
        this.photoClickListeners = photoClickListeners;
        this.imageLoad = engine;
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

        PhotoObject photoObject = photos.get(position);

        if (photoObject.eItemType == EItemType.CAMERA){
            holder.image.setImageResource(R.drawable.ico_camera_100_black);
            holder.image.setPadding(80,80,80,80);
            holder.textView.setText("Open Camera");
            holder.textView.setVisibility(View.VISIBLE);
            return;
        }
        if (photoObject.eItemType == EItemType.LOADING){
            holder.image.setImageResource(R.drawable.placeholder);
            return;
        }

        String uri = photos.get(position).path;
        imageLoad.loadImage(uri,holder.image);
    }

    @Override
    public void onViewRecycled(PhotosRecyclerViewHolder holder) {
        Glide.with(context).clear(holder.image);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


    class PhotosRecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ImageView image;

        public PhotosRecyclerViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.recycler_view_photo_image);
            textView = (TextView) v.findViewById(R.id.recycler_view_photo_text);
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
