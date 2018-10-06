package com.simson.www.ui.home;


import com.google.gson.Gson;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.HomeModel;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;

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
        mModel.getHomeBannerData(json,observer);
        addDisposable(observer);
    }

}
