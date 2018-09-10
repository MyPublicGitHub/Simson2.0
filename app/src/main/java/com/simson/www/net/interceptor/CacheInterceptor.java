package com.simson.www.net.interceptor;

import com.simson.www.application.AppContext;
import com.simson.www.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 设置缓存策略
 * author:
 * date: 2018/5/24
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkUtils.isAvailable(AppContext.getContext())) {

            //有网络时只从网络获取
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
        }
        Response response = chain.proceed(request);
        //设置缓存
        if (NetworkUtils.isAvailable(AppContext.getContext())) {
            //有网络时，设置缓存超时为0
            int maxAge = 0;
            response = response.newBuilder()
                    .removeHeader("Pramga")//清除头消息
                    .header("Cache-Control", "public,max-age=" + maxAge)
                    .build();
        }
        return response;
    }
}
