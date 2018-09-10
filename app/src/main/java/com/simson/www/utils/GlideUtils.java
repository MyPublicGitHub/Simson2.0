package com.simson.www.utils;

import android.widget.ImageView;

import com.simson.www.application.GlideApp;


/**
 * Glide图片加载管理类
 */

public class GlideUtils {

    public static void with(Object url,ImageView imageView) {
        GlideApp.with(imageView.getContext()).load(url).into(imageView);
    }

}
