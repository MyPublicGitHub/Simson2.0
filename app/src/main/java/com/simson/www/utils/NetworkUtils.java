package com.simson.www.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具
 * author:
 * date: 2018/5/24
 */

public class NetworkUtils {

    //判断是否有网络连接
    public static boolean isAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
            return networkInfo.isAvailable();
        return false;
    }
}
