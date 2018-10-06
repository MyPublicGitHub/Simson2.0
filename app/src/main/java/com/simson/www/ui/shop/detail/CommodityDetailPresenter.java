package com.simson.www.ui.shop.detail;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.CommodityDetailModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class CommodityDetailPresenter extends CommonPresenter<CommodityDetailContract.View>
        implements CommodityDetailContract.Presenter{
    private CommodityDetailModel mModel;
    private CommodityDetailContract.View mView;

    CommodityDetailPresenter() {
        this.mModel = new CommodityDetailModel();
    }
    @Override
    public void submitOrder() {
        mView = getView();
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
        map.put("itemIds", mView.getItemId());//itemIds：项目id多个逗号隔开必填
        map.put("buyNums", "1");//buyNums：购买数量多个逗号隔开必填
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
        String json = new Gson().toJson(map);
        mModel.saveShopCart(json, observer);
        addDisposable(observer);
    }

    @Override
    public void collect() {
        mView = getView();
        collect(mView.getItemId(),mView.getMethod(),mView.getType());
    }

}
