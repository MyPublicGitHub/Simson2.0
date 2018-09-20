package com.simson.www.net.api;

import com.simson.www.common.UrlConstainer;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.DiaryDetailAppendBean;
import com.simson.www.net.bean.community.DiaryDetailBean;
import com.simson.www.net.bean.community.DiaryDetailRecommendBean;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.bean.home.HomeItemBean;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.net.bean.shop.ShopListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口
 */

public interface ApiServer {

    //登录
    @POST(UrlConstainer.LOGIN)
    Observable<BaseBean<LoginBean>> login(@Query("json") String json);

    @POST(UrlConstainer.GET_CODE)
    Observable<BaseBean<CodeBean>> getCode(@Query("json") String json);

    //注册
    @POST(UrlConstainer.REGISTER)
    Observable<BaseBean<String>> register(@Query("json") String json);

    @POST(UrlConstainer.ITEM_TYPE)
    Observable<BaseBean<List<ItemTypeBean>>> getItemType(@Query("json") String json);

    //获取Banner信息
    @POST("indexController/menuPicture")
    Observable<BaseBean<HomeBannerBean>> getHomeBannerData(@Query("json") String json);

    //首页列表
    @POST(UrlConstainer.HOME_ITEM)
    Observable<BaseBean<List<HomeItemBean>>> getHomeItemData(@Query("json") String json);

    @POST("itemController/itemList")
    Observable<BaseBean<List<ShopListBean>>> getShopList(@Query("json") String json);

    @POST("itemController/itemDetail")
    Observable<BaseBean<CommodityDetailBean>> getCommodityDetail(@Query("json") String json);

    @POST("itemController/iemDetailPicture")
    Observable<BaseBean<CommodityDetailBean>> getCommodityDetailPicture(@Query("json") String json);

    @POST("itemController/getItemPraise")
    Observable<BaseBean<CommodityDetailPraiseBean>> getCommodityDetailPraise(@Query("json") String json);

    //日记列表
    @POST("diaryController/diaryList")
    Observable<BaseBean<List<DiaryBean>>> getDiaryList(@Query("json") String json);

    //日记详情
    @POST("diaryController/diaryDetail")
    Observable<BaseBean<DiaryDetailBean>> getDiaryDetail(@Query("json") String json);

    //追加日记
    @POST("diaryController/appendDiaryList")
    Observable<BaseBean<List<DiaryDetailAppendBean>>> getDiaryDetailAppend(@Query("json") String json);

    //日记推荐
    @POST("diaryController/diaryRecommend")
    Observable<BaseBean<List<DiaryDetailRecommendBean>>> getDiaryDetailRecommend(@Query("json") String json);

    //科普
    @POST("popularizationController/popularizationList")
    Observable<BaseBean<List<PopularizationBean>>> getPopularizationList(@Query("json") String json);
}
