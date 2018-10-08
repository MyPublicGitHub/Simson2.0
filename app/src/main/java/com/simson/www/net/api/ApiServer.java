package com.simson.www.net.api;

import com.simson.www.common.UrlConstainer;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.DiaryDetailAppendBean;
import com.simson.www.net.bean.community.DiaryDetailBean;
import com.simson.www.net.bean.community.DiaryDetailRecommendBean;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.net.bean.home.CauseListBean;
import com.simson.www.net.bean.home.CityListBean;
import com.simson.www.net.bean.home.DoctorDetailBean;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.bean.mine.AddressDetailBean;
import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.net.bean.mine.CustomerBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.bean.mine.IntegralDetailBean;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.net.bean.mine.SignBean;
import com.simson.www.net.bean.mine.SignPageBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.bean.shop.CommentBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.net.bean.shop.ShopListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口
 */

public interface ApiServer {

    //脱发原因列表
    @POST("alopeciaCauseController/alopeciaCauseList")
    Observable<BaseBean<List<CauseListBean>>> getCauseList(@Query("json") String json);

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
    Observable<BaseBean<List<DiaryBean>>> getHomeItemData(@Query("json") String json);

    @POST("itemController/itemList")
    Observable<BaseBean<List<ShopListBean>>> getShopList(@Query("json") String json);

    @POST("itemController/itemDetail")
    Observable<BaseBean<CommodityDetailBean>> getCommodityDetail(@Query("json") String json);

    @POST("itemController/iemDetailPicture")
    Observable<BaseBean<CommodityDetailBean>> getCommodityDetailPicture(@Query("json") String json);

    @POST("itemController/getItemPraise")
    Observable<BaseBean<CommodityDetailPraiseBean>> getCommodityDetailPraise(@Query("json") String json);

    //预约流程
    @POST("itemController/subscribeProcess")
    Observable<BaseBean<CommodityDetailBean>> getCommoditySubscribeProcess(@Query("json") String json);

    //评价列表
    @POST("itemController/queryItemComment")
    Observable<BaseBean<List<CommentBean>>> getCommodityQueryItemComment(@Query("json") String json);

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

    //专家问答
    @POST("questionsAnswer/questionsList")
    Observable<BaseBean<List<QuestionsBean>>> getQuestionsList(@Query("json") String json);

    //医生列表
    @POST("doctorController/doctorList")
    Observable<BaseBean<DoctorBean>> getDoctorList(@Query("json") String json);

    //医生详情
    @POST("doctorController/doctorDetail")
    Observable<BaseBean<DoctorDetailBean>> getDoctorDetail(@Query("json") String json);

    //我的
    @POST("customerController/byCustomer")
    Observable<BaseBean<CustomerBean>> getCustomer(@Query("json") String json);

    //收货地址
    @POST("deliveryController/deliveryList")
    Observable<BaseBean<List<AddressBean>>> getAddress(@Query("json") String json);

    //收货地址
    @POST("deliveryController/deliveryDetail")
    Observable<BaseBean<AddressDetailBean>> getAddressDetail(@Query("json") String json);

    //新增收货地址
    @POST("deliveryController/saveDelivery")
    Observable<BaseBean> newAddress(@Query("json") String json);

    //编辑收货地址
    @POST("deliveryController/updateDelivery")
    Observable<BaseBean> editAddress(@Query("json") String json);

    //购物车列表
    @POST("shoppingCart/shoppingCartList")
    Observable<BaseBean<List<ShopCartBean>>> getShopCart(@Query("json") String json);

    //移除购物车
    @POST("shoppingCart/removeShoppingCart")
    Observable<BaseBean> removeShopCart(@Query("json") String json);

    //修改购物车
    @POST("shoppingCart/updateShoppingCart")
    Observable<BaseBean> updateShopCart(@Query("json") String json);

    //提交订单
    @POST("transaction/submitOrder")
    Observable<BaseBean<SubmitOrderBean>> submitOrder(@Query("json") String json);

    //加入购物车
    @POST("shoppingCart/saveShoppingCart")
    Observable<BaseBean> saveShopCart(@Query("json") String json);

    //订单
    @POST("transaction/orderList")
    Observable<BaseBean<List<OrderBean>>> getOrder(@Query("json") String json);

    //订单支付
    @POST("transaction/paymentOrder")
    Observable<BaseBean<PaymentOrderBean>> paymentOrder(@Query("json") String json);

    //收藏
    @POST("customerController/collect")
    Observable<BaseBean> collect(@Query("json") String json);

    //关注
    @POST("customerController/follow")
    Observable<BaseBean> follow(@Query("json") String json);

    //赞
    @POST("customerController/praise")
    Observable<BaseBean> praise(@Query("json") String json);

    //案例
    @POST("caseController/caseList")
    Observable<BaseBean<List<CaseBean>>> getCase(@Query("json") String json);

    //城市列表
    @POST("hospitalController/cityList")
    Observable<BaseBean<List<CityListBean>>> getCityList(@Query("json") String json);

    //医院列表
    @POST("hospitalController/hospitalList")
    Observable<BaseBean<List<HospitalItemBean>>> getHospital(@Query("json") String json);

    //医院详情
    @POST("hospitalController/hospitalDetail")
    Observable<BaseBean<HospitalDetailBean>> getHospitalDetail(@Query("json") String json);

    //医院设备
    @POST("deviceController/deviceList")
    Observable<BaseBean<List<HospitalDeviceBean>>> getHospitalDeviceList(@Query("json") String json);

    //个人信息
    @POST("customerController/getCustomerInfo")
    Observable<BaseBean<CustomerInfoBean>> getCustomerInfo(@Query("json") String json);

    //个人信息
    @FormUrlEncoded
    @POST("customerController/updateCustomerInfo")
    Observable<BaseBean> updateCustomerInfo(@Field("json") String json);

    //重置密码获取短信
    @POST("customerController/pwdCode")
    Observable<CodeBean> pwdCode(@Query("json") String json);

    //重置密码
    @POST("customerController/updateCustomerPwd")
    Observable<BaseBean> updateCustomerPwd(@Query("json") String json);

    //签到
    @POST("signInController/signInPage")
    Observable<BaseBean<SignPageBean>> signInPage(@Query("json") String json);

    //签到
    @POST("signInController/signIn")
    Observable<BaseBean<SignBean>> signIn(@Query("json") String json);

    //积分明细
    @POST("pointController/pointList")
    Observable<BaseBean<List<IntegralDetailBean>>> pointList(@Query("json") String json);

    //收藏
    @POST("itemController/itemCollectList")
    Observable<BaseBean<List<ShopListBean>>> itemCollectList(@Query("json") String json);
}
