package com.simson.www.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.simson.www.R;
import com.simson.www.application.AppContext;


/**
 * Glide图片加载管理类
 * author:
 * date: 2018/2/26
 */

public class GlideLoaderManager {
    private static RequestOptions nomal_image_options = RequestOptions.placeholderOf(R.drawable.iv_img_default)
            .centerCrop();
    private static RequestOptions head_options = RequestOptions.placeholderOf(R.mipmap.ic_launcher_round);
    private static RequestOptions options = new RequestOptions().placeholder(R.drawable.iv_img_default)
            .error(R.drawable.iv_img_default);

    public static void loadImage(String url, final ImageView imageView) {
        Glide.with(AppContext.getContext()).load(url).apply(options).into(imageView);
    }

}
