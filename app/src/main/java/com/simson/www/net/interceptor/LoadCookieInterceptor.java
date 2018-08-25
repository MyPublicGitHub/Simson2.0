package com.simson.www.net.interceptor;

import android.text.TextUtils;

import com.simson.www.utils.SPUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加Cookie
 * author:
 * date: 2018/2/27
 */

public class LoadCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String mCookieStr = (String) SPUtils.get(chain.request().url().host(), "");
        if (!TextUtils.isEmpty(mCookieStr)) {
            builder.addHeader("Cookie", mCookieStr.substring(0, mCookieStr.length() - 1));//长度减1为了去除最后的逗号
        }
        return chain.proceed(builder.build());
    }
}
