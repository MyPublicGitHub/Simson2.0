package com.simson.www.net;

import android.content.Context;

import com.simson.www.net.api.ApiServer;
import com.simson.www.net.interceptor.CacheInterceptor;
import com.simson.www.net.interceptor.LoadCookieInterceptor;
import com.simson.www.net.interceptor.LogInterceptor;
import com.simson.www.net.interceptor.RequestInterceptor;
import com.simson.www.net.interceptor.SaveCookieInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类
 */
public class RxRetrofit {
    private Retrofit retrofit;
    private static ApiServer apiServer;

    private static final class Holder {
        private static final RxRetrofit INSTANCE = new RxRetrofit();
    }

    public void initRxRetrofit(final Context context, String baseUrl) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //链接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                //读取超时
                .readTimeout(10, TimeUnit.SECONDS)
                //缓存
                .cache(new Cache(context.getExternalFilesDir("http_cache"), 10 << 20))
                //添加Cookie拦截器
                .addInterceptor(new SaveCookieInterceptor())
                .addInterceptor(new LoadCookieInterceptor())
                .addInterceptor(new LogInterceptor())
                //添加缓存拦截器
                .addInterceptor(new RequestInterceptor())//无网
                .addNetworkInterceptor(new CacheInterceptor())//有网
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServer = retrofit.create(ApiServer.class);
    }

    public static RxRetrofit getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取ApiServer对象
     *
     * @return apiServer
     */
    public static ApiServer Api() {
        if (apiServer == null)
            throw new IllegalStateException("You must invoke init method first in Application");
        return apiServer;
    }


}
