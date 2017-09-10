# GalleryAndCameraImagePicker




## Demo [Video](https://youtu.be/ip0RlWPMN4k) 
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
