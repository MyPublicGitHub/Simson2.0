package com.simson.www.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        GlideUtils.with(o, imageView);
    }
}