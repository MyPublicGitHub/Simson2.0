package com.simson.www.net.api;

import com.simson.www.common.UrlConstainer;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.net.bean.home.HomeHeaderBean;

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
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST(UrlConstainer.LOGIN)
    Observable<BaseBean> login(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     *
     * @param username   用户名
     * @param password   密码
     * @param repassword 重复密码
     * @return
     */
    @FormUrlEncoded
    @POST(UrlConstainer.REGISTER)
    Observable<BaseBean<String>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


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
