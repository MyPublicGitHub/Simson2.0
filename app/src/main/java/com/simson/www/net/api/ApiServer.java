package com.simson.www.net.api;

import com.simson.www.common.UrlConstainer;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.net.bean.home.HomeHeaderBean;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.LoginBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口
 */

public interface ApiServer {

    /**
     * 登录
     */
    @GET(UrlConstainer.LOGIN)
    Observable<BaseBean<LoginBean>> login(@Query("json") String json);

    @GET(UrlConstainer.GET_CODE)
    Observable<BaseBean<CodeBean>> getCode(@Query("json") String json);

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST(UrlConstainer.REGISTER)
    Observable<BaseBean<String>> register(@Query("json") String json);


    /**
     * 获取首页头部信息
     */
    @GET("loginController/homepage")
    Observable<BaseBean<HomeHeaderBean>> getHomeHeader();

    /**
     * 首页文章列表
     *
     * @return
     */
    @GET(UrlConstainer.HOME_LIST)
    Observable<BaseBean<List<HomeDataBean>>> getHomeList(@Query("customerId") String customerId);
}
