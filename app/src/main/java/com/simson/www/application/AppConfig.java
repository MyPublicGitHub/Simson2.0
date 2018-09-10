package com.simson.www.application;

import android.content.Context;

import com.simson.www.BuildConfig;
import com.simson.www.common.UrlConstainer;
import com.simson.www.net.RxRetrofit;
import com.simson.www.utils.SPUtils;


/**
 * author:
 * date: 2018/3/13
 */

public class AppConfig {

    static void init(Context context){
        String baseUrl;
        if (BuildConfig.DEBUG){
            baseUrl = UrlConstainer.baseUrl_DEBUG;
        }else {
            baseUrl = UrlConstainer.baseUrl_RELEASE;
        }
        //初始化网络框架
        RxRetrofit.getInstance().initRxRetrofit(context, baseUrl);
        //初始化缓存
        SPUtils.init(context);
    }

}
