package com.simson.www.ui.home;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.HomeModel;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class HomePresenter extends CommonPresenter<HomeContract.View> implements HomeContract.Presenter {
    private HomeModel mModel;
    private HomeContract.View mView;

    HomePresenter() {
        this.mModel = new HomeModel();
    }

    @Override
    public void getBanner() {
        mView = getView();
        RxObserver<HomeBannerBean> observer = new RxObserver<HomeBannerBean>(this) {

            @Override
            public void onSuccess(HomeBannerBean mData) {
                mView.setBannerData(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        String json = new Gson().toJson(map);
        mModel.getHomeBannerData(json, observer);
        addDisposable(observer);
    }

    @Override
    public void indexSynchysis() {
        mView = getView();
        RxObserver<IndexSynchysisBean> observer = new RxObserver<IndexSynchysisBean>(this) {

            @Override
            public void onSuccess(IndexSynchysisBean mData) {
                //mView.indexSynchysis(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId",  SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));
        String json = new Gson().toJson(map);
        mModel.indexSynchysis(json, observer);
        addDisposable(observer);
    }

}
