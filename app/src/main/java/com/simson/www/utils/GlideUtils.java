package com.simson.www.utils;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.simson.www.R;
import com.simson.www.application.GlideApp;


/**
 * Glide图片加载管理类
 */

public class GlideUtils {

    public static void with(Object url, ImageView imageView) {
        with(url, imageView, R.mipmap.ic_placeholder, R.mipmap.ic_error);
    }

    public static void with(Object url, ImageView imageView, @DrawableRes int placeholder) {
        with(url, imageView, placeholder, placeholder);
    }

    public static void with(Object url, ImageView imageView, @DrawableRes int placeholder, @DrawableRes int error) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .error(error)
                .into(imageView);
    }
}
