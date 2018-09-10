package com.simson.www.net.api;

import com.simson.www.common.UrlConstainer;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.bean.home.HomeItemBean;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.bean.main.LoginBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
    @GET(UrlConstainer.REGISTER)
    Observable<BaseBean<String>> register(@Query("json") String json);

    @GET(UrlConstainer.ITEM_TYPE)
    Observable<BaseBean<List<ItemTypeBean>>> getItemType();


    /**
     * 获取Banner信息
     */
    @GET("indexController/menuPicture")
    Observable<BaseBean<HomeBannerBean>> getHomeBannerData();

    /**
     * 首页列表
     */
    @GET(UrlConstainer.HOME_ITEM)
    Observable<BaseBean<List<HomeItemBean>>> getHomeItemData(@Query("json") String json);
}
