package com.simson.www.ui.shop.detail;


import com.google.gson.Gson;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.CommodityDetailModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;

import java.util.HashMap;
import java.util.Map;


public class CommodityDetailPresenter extends BasePresenter<CommodityDetailContract.View> implements CommodityDetailContract.Presenter {
    private CommodityDetailModel mModel;
    private CommodityDetailContract.View mView;

    CommodityDetailPresenter() {
        this.mModel = new CommodityDetailModel();
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


}
