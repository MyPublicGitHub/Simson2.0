package com.simson.www.net.api;

import com.simson.www.common.UrlConstainer;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.DiaryDetailAppendBean;
import com.simson.www.net.bean.community.DiaryDetailBean;
import com.simson.www.net.bean.community.DiaryDetailRecommendBean;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.community.FriendsCircleBean;
import com.simson.www.net.bean.community.FriendsCircleCommentBean;
import com.simson.www.net.bean.community.FriendsCircleDetailBean;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.net.bean.community.QuestionsDetailBean;
import com.simson.www.net.bean.home.AlopeciaTestBean;
import com.simson.www.net.bean.home.CauseListBean;
import com.simson.www.net.bean.home.CityListBean;
import com.simson.www.net.bean.home.DoctorDetailBean;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.bean.home.HospitalBean;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.net.bean.home.LatelyHospitalBean;
import com.simson.www.net.bean.home.QuestionBean;
import com.simson.www.net.bean.home.TechnologyBean;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.net.bean.main.NewestRedEnvelopeBean;
import com.simson.www.net.bean.main.ProgramBean;
import com.simson.www.net.bean.main.ReceiveRedEnvelopeBean;
import com.simson.www.net.bean.main.VoteBean;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.bean.mine.AddressDetailBean;
import com.simson.www.net.bean.mine.AlopeciaBean;
import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.net.bean.mine.CustomerBasicBean;
import com.simson.www.net.bean.mine.CustomerBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.bean.mine.FansBean;
import com.simson.www.net.bean.mine.FollowBean;
import com.simson.www.net.bean.mine.HospitalTestBean;
import com.simson.www.net.bean.mine.IntegralDetailBean;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.net.bean.mine.RedEnvelopesBean;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.net.bean.mine.SignBean;
import com.simson.www.net.bean.mine.SignPageBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.bean.mine.SubscribeListBean;
import com.simson.www.net.bean.mine.TransactionRecordBean;
import com.simson.www.net.bean.mine.VIPBean;
import com.simson.www.net.bean.shop.CommentBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.net.bean.shop.ShopListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 接口
 */

public interface ApiServer {
    //获取红包
    @POST()
    Observable<NewestRedEnvelopeBean> newestRedEnvelope(@Url String url);

    //打开红包
    @POST()
    Observable<ReceiveRedEnvelopeBean> receiveRedEnvelope(@Url String url, @Query("json") String json);

    //获取节目列表
    @GET()
    Observable<List<ProgramBean>> program(@Url String url);

    //投票
    @GET()
    Observable<VoteBean> vote(@Url String url);

    //红包记录
    @POST("")
    Observable<BaseBean<List<RedEnvelopesBean>>> redEnvelopesRecord(@Url String url,@Query("json") String json);

    //交易记录
    @POST("transaction/rechargeOrderList")
    Observable<BaseBean<List<TransactionRecordBean>>> rechargeOrderList(@Query("json") String json);

    //充值
    @POST("transaction/paymentRechargeOrder")
    Observable<BaseBean<PaymentOrderBean>> paymentRechargeOrder(@Query("json") String json);

    //新增预约
    @POST("subscribeController/saveSubscribe")
    Observable<BaseBean<BaseBean>> saveSubscribe(@Query("json") String json);

    //预约list
    @POST("subscribeController/subscribeList")
    Observable<BaseBean<List<SubscribeListBean>>> subscribeList(@Query("json") String json);

    //到院检测list
    @POST("hospitalTesting/hospitalTestingList")
    Observable<BaseBean<List<HospitalTestBean>>> hospitalTestingList(@Query("json") String json);

    //新增到院检测list
    @POST("hospitalTesting/saveHospitalTesting")
    Observable<BaseBean> saveHospitalTesting(@Query("json") String json);

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
    Observable<BaseBean<List<HomeBannerBean>>> getHomeBannerData(@Query("json") String json);

    //首页混排数据列表
    @POST("indexController/indexSynchysis")
    Observable<BaseBean<List<IndexSynchysisBean>>> indexSynchysis(@Query("json") String json);

    //
    @POST("alopeciaTesting/getQuestion")
    Observable<BaseBean<QuestionBean>> getQuestion(@Query("json") String json);

    //新增头发检测
    @FormUrlEncoded
    @POST("alopeciaTesting/saveAlopeciaTesting")
    Observable<BaseBean<AlopeciaTestBean>> saveAlopeciaTesting(@Field("json") String json);

    //首页列表
    @POST(UrlConstainer.HOME_ITEM)
    Observable<BaseBean<List<DiaryBean>>> getHomeItemData(@Query("json") String json);

    @POST("itemController/itemList")
    Observable<BaseBean<List<ShopListBean>>> getShopList(@Query("json") String json);

    @POST("itemController/itemDetail")
    Observable<BaseBean<CommodityDetailBean>> getCommodityDetail(@Query("json") String json);

    @POST("hospitalController/getLatelyHospital")
    Observable<BaseBean<LatelyHospitalBean>> getLatelyHospital(@Query("json") String json);

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

    //评价列表
    @POST("hospitalController/getPlantingTechnology")
    Observable<BaseBean<List<TechnologyBean>>> getPlantingTechnology(@Query("json") String json);

    //日记列表
    @POST("diaryController/diaryList")
    Observable<BaseBean<List<DiaryBean>>> getDiaryList(@Query("json") String json);

    //朋友圈列表
    @POST("friendsCircle/friendsCircleList")
    Observable<BaseBean<List<FriendsCircleBean>>> friendsCircleList(@Query("json") String json);

    //朋友圈详情
    @POST("friendsCircle/getFriendsCircle")
    Observable<BaseBean<FriendsCircleDetailBean>> getFriendsCircle(@Query("json") String json);

    //发朋友圈
    @FormUrlEncoded
    @POST("friendsCircle/saveFriendsCircle")
    Observable<BaseBean> saveFriendsCircle(@Field("json") String json);

    //朋友圈评论列表
    @POST("friendsCircle/fiendsCircleCommentList")
    Observable<BaseBean<List<FriendsCircleCommentBean>>> fiendsCircleCommentList(@Query("json") String json);

    //添加朋友圈评论
    @POST("friendsCircle/saveFriendsCircleComment")
    Observable<BaseBean> saveFriendsCircleComment(@Query("json") String json);

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

    //专家问答
    @FormUrlEncoded
    @POST("questionsAnswer/questions")
    Observable<BaseBean> questions(@Field("json") String json);

    //专家问答
    @POST("questionsAnswer/questionsDetail")
    Observable<BaseBean<QuestionsDetailBean>> questionsDetail(@Query("json") String json);

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

    //购物车列表
    @POST("customerController/getCustomerVIP")
    Observable<BaseBean<VIPBean>> getCustomerVIP(@Query("json") String json);

    //移除购物车
    @POST("shoppingCart/removeShoppingCart")
    Observable<BaseBean> removeShopCart(@Query("json") String json);

    //修改购物车
    @POST("shoppingCart/updateShoppingCart")
    Observable<BaseBean> updateShopCart(@Query("json") String json);

    //购物车提交订单
    @POST("transaction/submitShoppingCartOrder")
    Observable<BaseBean<SubmitOrderBean>> submitShoppingCartOrder(@Query("json") String json);

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

    //订单支付
    @POST("customerController/getCustomerBasicInfo")
    Observable<BaseBean<CustomerBasicBean>> getCustomerBasicInfo(@Query("json") String json);

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

    //医院下拉列表
    @POST("hospitalController/getHospitalList")
    Observable<BaseBean<List<HospitalBean>>> getHospitalList(@Query("json") String json);

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
    Observable<BaseBean<CodeBean>> pwdCode(@Query("json") String json);

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

    //
    @FormUrlEncoded
    @POST("customerController/feedback")
    Observable<BaseBean> feedback(@Field("json") String json);

    //粉丝
    @POST("customerController/queryMyFansList")
    Observable<BaseBean<List<FansBean>>> queryMyFansList(@Query("json") String json);


    @POST("customerController/queryMyFollowList")
    Observable<BaseBean<List<FollowBean>>> queryMyFollowList(@Query("json") String json);

    //脱发检测列表
    @POST("alopeciaTesting/alopeciaTestingList")
    Observable<BaseBean<List<AlopeciaBean>>> alopeciaTestingList(@Query("json") String json);
}
