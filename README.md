# ImagePicker

Android ImagePicker library use to choose images from Gallery and also provide an option to capture image from camera.

<br>

### Demo [Video](https://youtu.be/ip0RlWPMN4k) 
![](https://raw.githubusercontent.com/akshaykale/ImagePicker/master/media/demo_gif.gif "Demo gif")


<br>

### Install

Add following to application level ```build.gradle``` file<br>
```
dependencies {
    //...
    compile 'com.akshaykale.android:imagepicker:1.0'
}
```
<br>

### Usage

ImagePicker view can be loaded directly as a DialogFragment.

<br>

#### MainActivity.java

```
// instantiate the TimelineFragment
ImagePickerFragment imagePickerFragment = new ImagePickerFragment();

FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

//add on click lictener
imagePickerFragment.addOnClickListener(new ImagePickerListener() {
                @Override
                public void onPhotoClicked(PhotoObject photoObject) {
                    
                }

                @Override
                public void onCameraClicked(Bitmap image) {

                }
            });

imagePickerFragment.show(ft, "dialog");

```
<br>

#### Use different image loading library

For Image loading this library uses Picasso, But you can use any library you preffer to load the image.
For this, create a class ```ImageLoad``` which  ```implements ImageLoadingEngine``` 
```
public class ImageLoad implements ImageLoadingEngine {
    @Override
    public void onLoadImage(ImageView imageView, String uri) {
          // Use any library you prefer to load the image into the view
    }
}
```
And before loading the fragment into the container add following line of code.<br>
```mFragment.setImageLoadEngine(new ImageLoad(getApplicationContext()));```

<br>

#### PhotoObject
| Functions | Details |
|---|---|
| ```String getName()``` | Name the the file |
| ```String getPath()``` | Path of the file |
| ```String getCreated_timestamp()``` | Timestamp of the date created of the file |
| ```String getPicasa_id()```| Picasa id associated with photo. ```null``` if no picasa id is found|
| ```double getLat()``` | Latitude of the photo |
| ```double getLng()``` | Longitude of the photo |

<br>

#### Configuration

| Function | Usage |
|---|---|
|```addOnClickListener();```| Implement click events on the photos or camera <br>1. ```void onPhotoClicked(PhotoObject photoObject) {...}``` <br>2. ```void onCameraClicked(Bitmap image) {...}```|
|```disableDefaultCameraFunction(boolean status)```| Will not open the camera and on clicking the camera option will return null bitmap, So that you can implement any thing you want.|
|```disableDefaultCameraButton(boolean status)```| Will hide the camera button and only show Gallery images.|
|```void setImageLoadEngine(ImageLoadEngine engine)```| Set the custom login to load image into the recycler view. By default ImagePicker uses Glide 4.x, and has optimized memory management.|
|```void saveImageToGallery(Bitmap bitmap, String name)```| Will save the bitmap to default gallery. ```name``` will be the name of the file with extension.<br>Ex: ```new_img.jpg```|



<br>
<br>

```
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
```
