package com.simson.www.net.interceptor;

import com.simson.www.common.UrlConstainer;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.SPUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 保存Cookie
 * author:
 * date: 2018/2/27
 */

public class SaveCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        List<String> mCookieList = response.headers("Set-Cookie");
        //保存Cookie
        if (!mCookieList.isEmpty() && request.url().toString().endsWith(UrlConstainer.LOGIN)) {
            StringBuilder sb = new StringBuilder();
            for (String cookie : mCookieList) {
                //注意Cookie请求头字段中的每个Cookie之间用逗号或分号分隔
                sb.append(cookie).append(",");
            }
            SPUtils.put(response.request().url().host(), sb.toString());
            LogUtils.e("intercept: url : " + request.url());
        }
        return response;
    }
}
