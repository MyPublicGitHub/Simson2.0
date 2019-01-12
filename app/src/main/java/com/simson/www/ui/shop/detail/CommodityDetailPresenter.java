package com.simson.www.ui.shop.detail;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.LatelyHospitalBean;
import com.simson.www.net.bean.home.TechnologyBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.CommodityDetailModel;
import com.simson.www.ui.core.model.TechnologyModel;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.ui.shop.detail.technology.TechnologyContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommodityDetailPresenter extends CommonPresenter<CommodityDetailContract.View>
        implements CommodityDetailContract.Presenter, TechnologyContract.Presenter {
    private CommodityDetailModel mModel;
    private TechnologyModel mTechnologyModel;
    private CommodityDetailContract.View mView;

    CommodityDetailPresenter() {
        this.mModel = new CommodityDetailModel();
        this.mTechnologyModel = new TechnologyModel();
    }

    @Override
    public void getLatelyHospital() {
        mView = getView();
        if (TextUtils.isEmpty(mView.longitude()) || TextUtils.isEmpty(mView.latitude())) {
            //ToastUtils.showToast("获取位置失败");
            return;
        }
        RxObserver<LatelyHospitalBean> observer = new RxObserver<LatelyHospitalBean>(this) {

            @Override
            public void onSuccess(LatelyHospitalBean mData) {
                mView.getLatelyHospital(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("longitude", mView.longitude());//经度
        map.put("latitude", mView.latitude());//纬度
        String json = new Gson().toJson(map);
        mModel.getLatelyHospital(json, observer);
        addDisposable(observer);
    }

    @Override
    public void submitOrder() {
        mView = getView();
        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""))) {
            ToastUtils.showToast("请先登录");
            return;
        }
        RxObserver<SubmitOrderBean> observer = new RxObserver<SubmitOrderBean>(this) {

            @Override
            public void onSuccess(SubmitOrderBean mData) {
                mView.showSubmitOrder(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("itemId", mView.getItemId());//itemIds：项目id多个逗号隔开必填
        map.put("buyNum", mView.getBuyNum());//buyNums：购买数量多个逗号隔开必填
        map.put("hospitalId", mView.hospitalId());//医院id
        map.put("technologyId", mView.technologyId());//植发技术
        map.put("subscribeDate", mView.subscribeDate());//到院日期
        String json = new Gson().toJson(map);
        mModel.submitOrder(json, observer);
        addDisposable(observer);
    }

    @Override
    public void getCommodityDetail() {
        mView = getView();
        RxObserver<CommodityDetailBean> observer = new RxObserver<CommodityDetailBean>(this) {

            @Override
            public void onSuccess(CommodityDetailBean mData) {
                mView.showCommodityDetail(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("itemId", mView.getItemId());
        String json = new Gson().toJson(map);
        mModel.getCommodityDetail(json, observer);
        addDisposable(observer);
    }

    @Override
    public void getCommodityDetailPicture() {
        mView = getView();
        RxObserver<CommodityDetailBean> observer = new RxObserver<CommodityDetailBean>(this) {

            @Override
            public void onSuccess(CommodityDetailBean mData) {
                mView.showCommodityDetailPicture(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("itemId", mView.getItemId());
        String json = new Gson().toJson(map);
        mModel.getCommodityDetailPicture(json, observer);
        addDisposable(observer);
    }

    @Override
    public void getCommodityDetailPraise() {
        mView = getView();
        RxObserver<CommodityDetailPraiseBean> observer = new RxObserver<CommodityDetailPraiseBean>(this) {

            @Override
            public void onSuccess(CommodityDetailPraiseBean mData) {
                mView.showCommodityDetailPraise(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("itemId", mView.getItemId());
        String json = new Gson().toJson(map);
        mModel.getCommodityDetailPraise(json, observer);
        addDisposable(observer);
    }

    @Override
    public void saveShopCart() {
        mView = getView();
        RxBaseObserver<BaseBean> observer = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean<BaseBean> bean) {
                //请求成功
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    mView.showSaveShopCart(bean);
                } else {
                    //失败
                    mView.showFail(bean.message);
                }
            }
        };

        Map<String, String> map = new HashMap();
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("timestamp", DateUtils.getStringDate());
        map.put("itemId", mView.getItemId());//项目id必填
        map.put("buyNum", mView.getBuyNum());//购物数量必填
       // map.put("buyNum", mView.getBuyNumSave());//购物数量必填
        map.put("hospitalId", mView.hospitalId());//医院id
        map.put("technologyId", mView.technologyId());//植发技术
        map.put("subscribeDate", mView.subscribeDate());//到院日期
        //map.put("subscribeDate", mView.subscribeDateSave());//到院日期
        String json = new Gson().toJson(map);
        mModel.saveShopCart(json, observer);
        addDisposable(observer);
    }

    @Override
    public void collect() {
        mView = getView();
        collect(mView.getItemId(), mView.getMethod(), mView.getType());
    }

    @Override
    public void getPlantingTechnology() {
        mView = getView();
        RxObserver<List<TechnologyBean>> observer = new RxObserver<List<TechnologyBean>>(this) {

            @Override
            public void onSuccess(List<TechnologyBean> mData) {
                mView.getPlantingTechnology(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        LogUtils.e("hospitalId:"+mView.hospitalId()+";itemTypeId:"+mView.itemTypeId());
        Map map = new HashMap();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("hospitalId", mView.hospitalId());
        map.put("itemTypeId", mView.itemTypeId());
        String json = new Gson().toJson(map);
        mTechnologyModel.getPlantingTechnology(json, observer);
        addDisposable(observer);
    }
}
